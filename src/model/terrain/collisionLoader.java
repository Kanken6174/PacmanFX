package model.terrain;

import java.io.*;

public class collisionLoader {
    public static Case[][] loadCollisions(String StageName, int maxX, int maxY){
        File collisionMap = new File("./out/production/pacmanfx/Cartes/"+StageName+"/"+StageName+".collisionMap");
        Case[][] cases = new Case[maxX][maxY];
        try (BufferedReader br = new BufferedReader(new FileReader(collisionMap))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < maxX) {
                int j = 0;
                for(char c : line.toCharArray()){
                    cases[i][j] = new Case();
                    cases[i][j].setEstObstacle((c=='1'));
                    j++;
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cases;
    }
}
