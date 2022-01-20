/**@author Yorick Geoffre
 * @brief contient les sources du Sprite
 */

package views.viewClasses.Sprites;


import javafx.scene.image.WritableImage;

/**
 * Cette classe est un wrapper autour d'une WritableImage qui sera utilisée par les vues.
 * Cette version est statique.
 */
public class Sprite {
    private WritableImage bi;
    public Sprite(WritableImage bi){
        this.bi = bi;
    }

    public Sprite() {
        this.bi = null;
    }

    public WritableImage getImage(){
        return bi;
    }

    public void setBi(WritableImage bi){
        this.bi = bi;
    }
}
