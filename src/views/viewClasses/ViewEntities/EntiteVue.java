package views.viewClasses.ViewEntities;

import javafx.scene.image.WritableImage;
import model.entites.Entite;
import views.viewClasses.Sprites.Sprite;

public class EntiteVue {

    protected Sprite ressourceLocale = null;
    protected Entite source = null;

    public EntiteVue(Sprite sp, Entite e){
        this.ressourceLocale = sp;
        this.source = e;
    }

    public WritableImage getImage(){
        return ressourceLocale.getImage();
    }

    public Entite getSource(){
        return source;
    }
}