/**@Author Yorick Geoffre
 * @brief ce fichier contient les sources du chargeur d'entités
 * */


package model.terrain.loaders;

import model.entites.*;
import model.enums.FantomeNom;
import model.fileData.SpriteAnchor;
import model.terrain.Case;

import java.io.*;

/**L'entity loader va charger les entités depuis le fichier .entityMap qui va de paire avec le fichier .collisionMap*/
public class entityLoader {
    /**
     * va charger les entités (pacman, fantomes, fruits, gommes...) depuis le fichier .entityMap
     * @param StageName le nom du niveau à charger
     * @param cases Le double tableau rendu par le chargeur de collision (terrain sans entités)
     * @param maxX  La taille du tableau en X
     * @param maxY  La taille du tableau en Y
     * @return  Le double tableau du terrain avec collisions+entités chargées
     */
    public static Case[][] loadEntities(String StageName, Case[][] cases, int maxX, int maxY){
        File entityMap = new File("./out/production/pacmanfx/Cartes/"+StageName+"/"+StageName+".entityMap");

        try (BufferedReader br = new BufferedReader(new FileReader(entityMap))) {   //on lit le fichier
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < maxX) {    //ligne par ligne
                int j = 0;
                for(char c : line.toCharArray()){       //caractère par caractère
                    switch (c){
                        case '1':   //on utilise le chiffre lu pour déterminer quoi mettre à cet emplacement
                            Gomme g = new Gomme();
                            g.setLogicRow(i);
                            g.setLogicColumn(j);
                            g.setHome(i,j);
                            g.setSpriteAnchor(null);
                            cases[i][j].ReceiveStaticEntity(g);
                            break;
                        case '2':
                            Pacman pacman = new Pacman();
                            pacman.setLogicRow(i);
                            pacman.setLogicColumn(j);
                            pacman.setHome(i,j);
                            pacman.setSpriteAnchor(null);
                            cases[i][j].ReceiveEntity(pacman);
                            break;
                        case '3':
                            Fantome blinky = new Fantome(FantomeNom.BLINKY);
                            blinky.setLogicRow(i);
                            blinky.setLogicColumn(j);
                            blinky.setHome(i,j);
                            blinky.setSpriteAnchor(new SpriteAnchor(456,16*blinky.getFantomeNom().ordinal(),0,
                                    "./out/production/pacmanfx/Images/sprites.png",16,16*8,true));
                            cases[i][j].ReceiveEntity(blinky);
                            break;
                        case '4':
                            Fantome pinky = new Fantome(FantomeNom.INKY);
                            pinky.setLogicRow(i);
                            pinky.setLogicColumn(j);
                            pinky.setHome(i,j);
                            pinky.setSpriteAnchor(new SpriteAnchor(456,16*pinky.getFantomeNom().ordinal(),0,
                                    "./out/production/pacmanfx/Images/sprites.png",16,16*8,true));
                            cases[i][j].ReceiveEntity(pinky);
                            break;
                        case '5':
                            Fantome inky = new Fantome(FantomeNom.PINKY);
                            inky.setLogicRow(i);
                            inky.setLogicColumn(j);
                            inky.setHome(i,j);
                            inky.setSpriteAnchor(new SpriteAnchor(456,16*inky.getFantomeNom().ordinal(),0,
                                    "./out/production/pacmanfx/Images/sprites.png",16,16*8,true));
                            cases[i][j].ReceiveEntity(inky);
                            break;
                        case '6':
                            Fantome clyde = new Fantome(FantomeNom.CLYDE);
                            clyde.setLogicRow(i);
                            clyde.setLogicColumn(j);
                            clyde.setHome(i,j);
                            clyde.setSpriteAnchor(new SpriteAnchor(456,16*clyde.getFantomeNom().ordinal(),0,
                                    "./out/production/pacmanfx/Images/sprites.png",16,16*8,true));
                            cases[i][j].ReceiveEntity(clyde);
                            break;
                        case '7':
                            SuperGomme sg = new SuperGomme();
                            sg.setLogicRow(i);
                            sg.setLogicColumn(j);
                            sg.setHome(i,j);
                            cases[i][j].ReceiveStaticEntity(sg);
                            break;
                        case '8':
                            Fruits f = new Fruits();
                            f.setLogicRow(i);
                            f.setLogicColumn(j);
                            f.setHome(i,j);
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
