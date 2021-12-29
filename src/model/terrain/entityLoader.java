package model.terrain;

import model.entites.*;

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
                            Gomme g = new Gomme();
                            cases[i][j].ReceiveStaticEntity(g);
                            break;
                        case '2':
                            PacmanObject pacman = new PacmanObject();
                            cases[i][j].ReceiveEntity(pacman);
                            break;
                        case '3':
                            Fantome blinky = new Fantome(0);
                            cases[i][j].ReceiveEntity(blinky);
                            break;
                        case '4':
                            Fantome pinky = new Fantome(1);
                            cases[i][j].ReceiveEntity(pinky);
                            break;
                        case '5':
                            Fantome inky = new Fantome(2);
                            cases[i][j].ReceiveEntity(inky);
                            break;
                        case '6':
                            Fantome clyde = new Fantome(3);
                            cases[i][j].ReceiveEntity(clyde);
                            break;
                        case '7':
                            SuperGomme sg = new SuperGomme();
                            cases[i][j].ReceiveStaticEntity(sg);
                            break;
                        case '8':
                            Fruits f = new Fruits();
                            cases[i][j].ReceiveStaticEntity(f);
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

        return cases;
    }
}
