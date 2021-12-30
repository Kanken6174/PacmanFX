package model.graphics.Sprites;

import java.awt.image.BufferedImage;

public class SpriteAnimable extends Sprite {
    private BufferedImage Spritesheet;
    private int SpritesAmountX = 0;
    private int SpritesAmountY = 0;
    private int spriteHeight = 16;
    private int spriteWidth = 16;

    public SpriteAnimable(BufferedImage bi){
        Spritesheet = bi;
    }

    public SpriteAnimable(BufferedImage bi, int height, int width){
        Spritesheet = bi;
        spriteHeight = height;
        spriteWidth = width;
    }

    public BufferedImage getSpritesheet(){
        return Spritesheet;
    }

    public int getSpritesAmountX(){
        return SpritesAmountX;
    }

    public int getSpritesAmountY(){
        return SpritesAmountY;
    }

}
