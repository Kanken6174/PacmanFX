package model.graphics.tools;

import javafx.scene.image.WritableImage;

public class ImageRotate {
    public static WritableImage RotateDeg(WritableImage bi, double degresPos){
        degresPos /= 180;
        degresPos *= Math.PI;
        /*
        WritableImage toReturn = new WritableImage(bi.getHeight(),bi.getWidth(), TYPE_INT_ARGB);//RGB avec transparence

        Graphics2D g2d = toReturn.createGraphics();
        g2d.translate((bi.getHeight() - bi.getWidth()) / 2, (bi.getHeight() - bi.getWidth()) / 2);
        g2d.rotate(degresPos, bi.getHeight()/2, bi.getWidth()/2);
        g2d.drawRenderedImage(bi,null);
        */

        return bi;
    }
}
