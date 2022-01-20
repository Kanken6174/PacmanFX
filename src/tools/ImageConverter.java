/**@author Yorick Geoffre
 * @brief contient les sources des anciens convertisseurs d'images
 */

package tools;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

/**
 * @deprecated Cet ancien convertisseur était nécessaire pour passer les images en WritableImage et vice-versa.
 * Plus nécessaire dans le projet actuel.
 */
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
