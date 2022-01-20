/**@Author Yorick Geoffre
 * @brief ce fichier contient les sources du chargeur de collisions
 * */

package model.terrain.loaders;

import model.terrain.Case;

import java.io.*;

/**
 * Le CollisionLoader va charger les différentes cases du terrain ainsi que si elles ont une collision ou non.
 * Le chargement de ce données s'effectue depuis le fichier .collisionMap du niveau demandé
 */
public class CollisionLoader {
    /**
     * Charge le terrain et ses collisions
     * @param StageName Le niveau demandé
     * @param maxX  Le maximum de lignes dans le fichier
     * @param maxY  Le maximum de caractères (colonnes) dans le fichier, par ligne
     * @return  Un double tableau qui représente les cases du terrain
     */
    public static Case[][] loadCollisions(String StageName, int maxX, int maxY){
        File collisionMap = new File("./out/production/pacmanfx/Cartes/"+StageName+"/"+StageName+".collisionMap");
        Case[][] cases = new Case[maxX][maxY];
        try (BufferedReader br = new BufferedReader(new FileReader(collisionMap))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < maxX) {
                int j = 0;
                for(char c : line.toCharArray()){
                    cases[i][j] = new Case(i,j);
                    cases[i][j].setEstObstacle((c=='1'));
                    cases[i][j].setGhostHouseDoor((c=='2'));
                    j++;
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cases;
    }
}
