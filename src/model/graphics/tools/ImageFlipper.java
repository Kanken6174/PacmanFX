package model.graphics.tools;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class ImageFlipper {
    public static BufferedImage flipHorizontal(BufferedImage bi){
        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -bi.getHeight());
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        bi = op.filter(bi, null);
        return bi;
    }

    public static BufferedImage flipVertical(BufferedImage bi){
        BufferedImage b2 = bi;
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-bi.getWidth(), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        bi = op.filter(bi, null);
        return bi;
    }
}
