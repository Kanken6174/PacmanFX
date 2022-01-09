package views.Animateurs;

import views.Sprites.SpriteAnimable;


public class Animateur implements Runnable {
    private SpriteAnimable toAnimate;

    public Animateur(SpriteAnimable sa){
        this.toAnimate = sa;
    }

    protected void Animate(){
        toAnimate.nextFrame();
    }

    @Override
    public void run(){
        Animate();
    }
}
