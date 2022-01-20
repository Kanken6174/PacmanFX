/**@author Yorick Geoffre
 * @brief contient les sources de l'entite-Vue animable
 */

package views.viewClasses.ViewEntities;

import javafx.beans.property.ObjectProperty;
import javafx.scene.image.WritableImage;
import model.entites.Entite;
import views.viewClasses.Animateurs.Animateur;
import views.viewClasses.Sprites.SpriteAnimable;

/**
 * L'entieVueAnimable fait le lien entre une SpriteAnimable, sont entité, et son animateur.
 */
public class EntiteVueAnimable extends EntiteVue{
    /**L'animateur de la SpriteAnimable*/
    protected Animateur anim;

    /**
     * Le constructeur standard de l'entiteVue Animable
     * @param s
     * @param e
     */
    public EntiteVueAnimable(SpriteAnimable s,Entite e){
        super(s,e);
        this.anim = new Animateur((SpriteAnimable) super.ressourceLocale);
    }

    public ObjectProperty<WritableImage> getSpriteProperty(){
        return ((SpriteAnimable)this.ressourceLocale).CurrentFrameProperty();
    }

    public SpriteAnimable getSpriteAnimable(){
        return (SpriteAnimable) super.ressourceLocale;
    }

    @Override
    public WritableImage getImage(){
        return ((SpriteAnimable)this.ressourceLocale).getFrame();
    }
}
