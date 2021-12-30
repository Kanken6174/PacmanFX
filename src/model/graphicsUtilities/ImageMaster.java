package model.graphicsUtilities;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public class ImageMaster {
    private BufferedImage MainRessource;

    public ImageMaster(BufferedImage MainRessource){
        this.MainRessource = MainRessource;
    }

    public Image getImageAt(int x, int y){
        Image img = SwingFXUtils.toFXImage(ImageClipper.clipSprite(ImageLoader.loadRessources(),x,y),null);
        return img;
    }
}
