/**@author Yorick Geoffre
 * @brief contient les sources de l'animateur d'entités
 */
package views.viewClasses.Animateurs;

import javafx.application.Platform;
import model.boucles.Abonne;
import views.viewClasses.Sprites.SpriteAnimable;

/**
 * Cette classe permet d'animer la SpriteAnimable d'une entité (utilisé par les fantômes par exemple)
 */
public class Animateur implements Abonne {
    /**La sprite animable à animer*/
    private SpriteAnimable toAnimate;

    public Animateur(SpriteAnimable sa){
        this.toAnimate = sa;
    }

    /**
     * Va changer la frame actuelle de la SpriteAnimable à la prochaine du cycle sélectionné
     */
    protected void Animate(){
        toAnimate.nextFrame();
    }

    @Override
    public void doAction() {
        Platform.runLater(() -> {Animate();});  //lambda expression pour créer un runnable anonyme...
    }
}