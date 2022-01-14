package model.terrain.loaders;

import model.entites.*;
import model.enums.FantomeNom;
import model.fileData.SpriteAnchor;
import model.terrain.Case;

import java.io.*;

public class entityLoader {
    public static Case[][] loadEntities(String StageName, Case[][] cases, int maxX, int maxY){
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
                            g.setLogicX(i);
                            g.setLogicY(j);
                            g.setAnchor(null);
                            cases[i][j].ReceiveStaticEntity(g);
                            break;
                        case '2':
                            PacmanObject pacman = new PacmanObject();
                            pacman.setLogicX(i);
                            pacman.setLogicY(j);
                            pacman.setAnchor(null);
                            cases[i][j].ReceiveEntity(pacman);
                            break;
                        case '3':
                            Fantome blinky = new Fantome(FantomeNom.BLINKY);
                            blinky.setLogicX(i);
                            blinky.setLogicY(j);
                            blinky.setAnchor(new SpriteAnchor(456,16*blinky.getFantomeNom().ordinal(),0,
                                    "./out/production/pacmanfx/Images/sprites.png",16,16*8,true));
                            cases[i][j].ReceiveEntity(blinky);
                            break;
                        case '4':
                            Fantome pinky = new Fantome(FantomeNom.INKY);
                            pinky.setLogicX(i);
                            pinky.setLogicY(j);
                            pinky.setAnchor(new SpriteAnchor(456,16*pinky.getFantomeNom().ordinal(),0,
                                    "./out/production/pacmanfx/Images/sprites.png",16,16*8,true));
                            cases[i][j].ReceiveEntity(pinky);
                            break;
                        case '5':
                            Fantome inky = new Fantome(FantomeNom.PINKY);
                            inky.setLogicX(i);
                            inky.setLogicY(j);
                            inky.setAnchor(new SpriteAnchor(456,16*inky.getFantomeNom().ordinal(),0,
                                    "./out/production/pacmanfx/Images/sprites.png",16,16*8,true));
                            cases[i][j].ReceiveEntity(inky);
                            break;
                        case '6':
                            Fantome clyde = new Fantome(FantomeNom.CLYDE);
                            clyde.setLogicX(i);
                            clyde.setLogicY(j);
                            clyde.setAnchor(new SpriteAnchor(456,16*clyde.getFantomeNom().ordinal(),0,
                                    "./out/production/pacmanfx/Images/sprites.png",16,16*8,true));
                            cases[i][j].ReceiveEntity(clyde);
                            break;
                        case '7':
                            SuperGomme sg = new SuperGomme();
                            sg.setLogicX(i);
                            sg.setLogicY(j);
                            cases[i][j].ReceiveStaticEntity(sg);
                            break;
                        case '8':
                            Fruits f = new Fruits();
                            f.setLogicX(i);
                            f.setLogicY(j);
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
