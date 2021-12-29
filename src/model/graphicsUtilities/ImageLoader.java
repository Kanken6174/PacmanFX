package model.graphicsUtilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ImageLoader {
    public static BufferedImage loadRessources(){
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new File("./out/production/pacmanfx/Images/sprites.png"));    //chemin relatif
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi;
    }
}
