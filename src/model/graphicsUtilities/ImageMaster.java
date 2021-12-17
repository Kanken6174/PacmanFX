package model.graphicsUtilities;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class ImageMaster {
    public static Image getImageAt(int x, int y){
        Image img = SwingFXUtils.toFXImage(ImageClipper.clipSprite(ImageLoader.loadRessources(),x,y),null);
        return img;
    }
}
