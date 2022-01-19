package views.viewClasses.Sprites;


import javafx.scene.image.WritableImage;

public class Sprite {
    private WritableImage bi;
    public Sprite(WritableImage bi){
        this.bi = bi;
    }

    public Sprite() {
        this.bi = null;
    }

    public WritableImage getImage(){
        return bi;
    }

    public void setBi(WritableImage bi){
        this.bi = bi;
    }
}
