package model.graphics.Loaders;

import javafx.scene.image.Image;

import java.io.File;


public class ImageLoader {
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
}
