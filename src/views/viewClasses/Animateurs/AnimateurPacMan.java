/**@author Yorick Geoffre
 * @brief contient les sources de l'animateur de pacman
 */
package views.viewClasses.Animateurs;

import javafx.scene.image.WritableImage;
import javafx.scene.shape.Arc;
import views.viewClasses.Sprites.SpriteAnimable;

/**
 * Cette classe spécifie un comportement d'animation pour le pacman, qui est un Arc et donc
 * ne dispose pas de SpriteAnimable (animation directe de l'arc)
 */
public class AnimateurPacMan extends Animateur{
    private Arc pacman;
    private boolean closingAnimation = false;

    public AnimateurPacMan(Arc pacman){
        super(new SpriteAnimable(new WritableImage(1,1),0,0));
        this.pacman = pacman;
    }

    @Override
    protected void Animate(){
        double rotation = pacman.getRotate();
        if(pacman.getLength() >= 345){
            closingAnimation = true;
        }else if(pacman.getLength() < 270){
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
