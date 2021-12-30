package model.graphics.tools;

import java.awt.image.BufferedImage;

public class ImageClipper {
    public static BufferedImage clipSprite(BufferedImage source,int x, int y){
        return source.getSubimage(x,y,14,14);
    }

    public static BufferedImage clipGhostAnimation(BufferedImage source, int id){
        return source.getSubimage(441,49+(16*id),14,14*8);
    }

    public static BufferedImage getFrame(BufferedImage source, int id){
        return source.getSubimage(id*16,0,14,14);
    }

    public static BufferedImage getFrame(BufferedImage source, int id, int sideSize){
        return source.getSubimage(id*sideSize,0,sideSize,sideSize);
    }
}