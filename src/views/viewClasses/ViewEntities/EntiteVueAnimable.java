package views.viewClasses.ViewEntities;

import javafx.beans.property.ObjectProperty;
import javafx.scene.image.WritableImage;
import model.entites.Entite;
import views.viewClasses.Animateurs.Animateur;
import views.viewClasses.Sprites.SpriteAnimable;

public class EntiteVueAnimable extends EntiteVue{
    protected Animateur anim;

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
