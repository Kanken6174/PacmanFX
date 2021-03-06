/**@author Yorick Geoffre
 * @brief contient les sources des anciens flippers d'images (par axe)
 */

package tools;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * @deprecated Cet ancien flipper d'image servait à mirror les images sur un axe, il utilisait AWT et a donc été retiré.
 */
public class ImageFlipper {
    public static WritableImage flipHorizontal(WritableImage bi){
        /*
        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -bi.getHeight());
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        bi = op.filter(bi, null);
        */
        PixelWriter px = bi.getPixelWriter();
        return bi;
    }

    public static WritableImage flipVertical(WritableImage bi){
        /*
        WritableImage b2 = bi;
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-bi.getWidth(), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        bi = op.filter(bi, null);
        return bi;
        */
        PixelWriter px = bi.getPixelWriter();
        return bi;
    }
}
