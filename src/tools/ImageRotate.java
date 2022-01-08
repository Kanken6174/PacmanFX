package tools;

import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ImageRotate {
    public static WritableImage RotateDegx90(WritableImage bi, double degresPos){
        if(bi.getWidth() != bi.getHeight())
            throw new IndexOutOfBoundsException("Image must be a square");

        PixelReader pr = bi.getPixelReader();
        PixelWriter pw = bi.getPixelWriter();
        Color[][] bytes = new Color[(int)bi.getHeight()][(int)bi.getWidth()];

        for(int pixelX = 0; pixelX < bi.getWidth(); pixelX++) {
            for (int pixelY = 0; pixelY < bi.getWidth(); pixelY++) {
                bytes[pixelX][pixelY] = pr.getColor(pixelX,pixelY);
            }
        }

        for(int deg = 0; deg < degresPos; deg += 90)
            bytes = RotateColorArray90(bytes[0].length,bytes);

        for(int pixelX = 0; pixelX < bi.getWidth(); pixelX++) {
            for (int pixelY = 0; pixelY < bi.getWidth(); pixelY++) {
                 pw.setColor(pixelX,pixelY,bytes[pixelX][pixelY]);
            }
        }

        return bi;

        /* vieux code

        WritableImage toReturn = new WritableImage(bi.getHeight(),bi.getWidth(), TYPE_INT_ARGB);//RGB avec transparence

        Graphics2D g2d = toReturn.createGraphics();
        g2d.translate((bi.getHeight() - bi.getWidth()) / 2, (bi.getHeight() - bi.getWidth()) / 2);
        g2d.rotate(degresPos, bi.getHeight()/2, bi.getWidth()/2);
        g2d.drawRenderedImage(bi,null);
        */
    }

    private static Color[][] RotateColorArray90(int side,Color colors[][]){
        for (int x = 0; x < side / 2; x++) {
            for (int y = x; y < side-x-1; y++) {
                Color temp = colors[x][y];
                colors[x][y] = colors[y][side-1-x];
                colors[y][side-1-x] = colors[side-1-x][side-1-y];
                colors[side-1-x][side-1-y] = colors[side-1-y][x];
                colors[side-1-y][x] = temp;
            }
        }
        return colors;
    }
}
