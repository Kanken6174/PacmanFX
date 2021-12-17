package model.utilities;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ImageLoader {
    public static BufferedImage loadRessources(){
        BufferedImage bi = null;
        try {
            //bi = ImageIO.read(new File("/home/IUT/yogeoffre/2A/javaFX/pacmanfx/ressources/Images/sprites.png"));
            bi = ImageIO.read(new File("/home/IUT/jominchin/Bureau/annee2/Semestre1/Periode2/JavaFX/pacmanfx/ressources/Images/sprites.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi;
    }
}
