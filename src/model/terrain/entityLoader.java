package model.terrain;

import model.entites.Fantome;
import model.entites.Pacman;

import java.io.*;

public class entityLoader {
    public static Case[][] loadEntities(String StageName, Case[][] cases,int maxX, int maxY){
        File entityMap = new File("./out/production/pacmanfx/Cartes/"+StageName+"/"+StageName+".entityMap");

        try (BufferedReader br = new BufferedReader(new FileReader(entityMap))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < maxX) {
                int j = 0;
                for(char c : line.toCharArray()){
                    switch (c){
                        case '1':
                            Pacman pacman = new Pacman();
                            cases[i][j].ReceiveEntity(pacman);
                            break;
                        case '2':
                            Fantome clyde = new Fantome(0);
                            cases[i][j].ReceiveEntity(clyde);
                            break;
                        case '3':   break;
                        case '4':   break;
                        case '5':   break;
                        default: break;
                    }

                    j++;
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
