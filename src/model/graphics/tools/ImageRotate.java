package model.graphics.tools;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.*;

public class ImageRotate {
    public static BufferedImage RotateDeg(BufferedImage bi, double degresPos){
        degresPos /= 180;
        degresPos *= Math.PI;

        BufferedImage toReturn = new BufferedImage(bi.getHeight(),bi.getWidth(), TYPE_INT_ARGB);//RGB avec transparence

        Graphics2D g2d = toReturn.createGraphics();
        g2d.translate((bi.getHeight() - bi.getWidth()) / 2, (bi.getHeight() - bi.getWidth()) / 2);
        g2d.rotate(degresPos, bi.getHeight()/2, bi.getWidth()/2);
        g2d.drawRenderedImage(bi,null);

        return toReturn;
    }
}
