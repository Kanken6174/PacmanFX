package views.ViewEntities;

import javafx.beans.property.ObjectProperty;
import javafx.scene.image.WritableImage;
import model.entites.Entite;
import views.Animateurs.Animateur;
import views.Sprites.SpriteAnimable;

public class EntiteVueAnimable extends EntiteVue{
    protected Animateur anim;

    public EntiteVueAnimable(SpriteAnimable s,Entite e){
        super(s,e);
        this.anim = new Animateur((SpriteAnimable) super.ressourceLocale);
    }

    public ObjectProperty<WritableImage> getSpriteProperty(){
        return ((SpriteAnimable)this.ressourceLocale).CurrentFrameProperty();
    }

    @Override
    public WritableImage getImage(){
        return ((SpriteAnimable)this.ressourceLocale).getFrame();
    }
}
