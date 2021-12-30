package model.graphics.Sprites;

import java.awt.image.BufferedImage;

public class Sprite {
    private BufferedImage bi;
    public Sprite(BufferedImage bi){
        this.bi = bi;
    }

    public Sprite() {
        this.bi = null;
    }

    public BufferedImage getImage(){
        return bi;
    }

    public void setBi(BufferedImage bi){
        this.bi = bi;
    }
}
