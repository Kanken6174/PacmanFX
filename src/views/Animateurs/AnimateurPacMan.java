package views.Animateurs;

import javafx.scene.image.WritableImage;
import javafx.scene.shape.Arc;
import views.Sprites.SpriteAnimable;

public class AnimateurPacMan extends Animateur{
    private Arc pacman;
    private boolean closingAnimation = false;

    public AnimateurPacMan(Arc pacman){
        super(new SpriteAnimable(new WritableImage(1,1),0,0));
        this.pacman = pacman;
    }

    @Override
    protected void Animate(){

        if(pacman.getLength() >= 345+pacman.getRotate()){
            closingAnimation = true;
        }else if(pacman.getLength() < 270+pacman.getRotate()){
            closingAnimation = false;
        }
        if (closingAnimation){
            pacman.setLength(pacman.getLength() - 3f);
            pacman.setStartAngle(pacman.getStartAngle()+1f);
        } else {
            pacman.setLength(pacman.getLength() + 3f);
            pacman.setStartAngle(pacman.getStartAngle()-1f);
        }
    }
}
