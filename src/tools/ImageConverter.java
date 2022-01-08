package tools;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;


public class ImageConverter {
    public static WritableImage ImageToWriteableImage(Image source){
        WritableImage wi = new WritableImage(source.getPixelReader(),(int)source.getWidth(),(int)source.getHeight());
        return wi;
    }

    public static Image WriteableImageToImage(WritableImage source){
        Image im = new Image(source.getUrl());
        return im;
    }
}
