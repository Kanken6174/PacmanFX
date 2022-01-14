package views.Animateurs;

import javafx.application.Platform;
import model.boucles.Abonne;
import views.Sprites.SpriteAnimable;


public class Animateur implements Abonne {
    private SpriteAnimable toAnimate;

    public Animateur(SpriteAnimable sa){
        this.toAnimate = sa;
    }

    protected void Animate(){
        toAnimate.nextFrame();
    }

    @Override
    public void doAction() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Animate();
            }
        });
    }
}
