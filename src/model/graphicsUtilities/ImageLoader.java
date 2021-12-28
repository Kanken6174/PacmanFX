package model.graphicsUtilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ImageLoader {
    public static BufferedImage loadRessources(){
        BufferedImage bi = null;
        try {
            //bi = ImageIO.read(new File("/home/IUT/yogeoffre/2A/javaFX/pacmanfx/ressources/Images/sprites.png"));
            //bi = ImageIO.read(new File("/home/IUT/yogeoffre/2A/javaFX/pacmanfx/ressources/Images/sprites.png"));
            bi = ImageIO.read(new File("Y:\\java\\projects\\pacmanfx\\ressources\\Images\\sprites.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi;
    }
}
