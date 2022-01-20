/**@author Yorick Geoffre
 * @brief contient les sources du chargeur de spritesheet
 */

package views.viewClasses.Loaders;

import javafx.scene.image.Image;

import java.io.File;

/**
 * Permet de charger une image soit depuis un chemin par défaut, soit depuis un chemin spécifique
 */
public class ImageLoader {
    /**
     * Version par défaut
     * @return la spritesheet par défaut
     */
    public static Image loadRessources(){
        Image img = null;
        try {
            File f = new File("./out/production/pacmanfx/Images/sprites.png");    //chemin relatif
            img = new Image(f.toURI().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * Version actuelle spécifiée
     * @param path le chemin de la spritesheet sur le disque
     * @return la spritesheet
     */
    public static Image getImageFromPath(String path){
        Image img = null;
        try {
            File f = new File(path);    //chemin relatif
            img = new Image(f.toURI().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }
}
