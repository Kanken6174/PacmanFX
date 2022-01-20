/**@author Yorick Geoffre
 * @brief contient les sources de l'entite-Vue
 */

package views.viewClasses.ViewEntities;

import javafx.scene.image.WritableImage;
import model.entites.Entite;
import views.viewClasses.Sprites.Sprite;

/**Une entiteVue fait la liaison entre une entité du modèle (Entite...) avec son Sprite*/
public class EntiteVue {
    /**La sprite correspondante*/
    protected Sprite ressourceLocale = null;
    /**L'entite correspondante*/
    protected Entite source = null;

    /**
     * Le constructeur standard de l'entiteVue
     * @param sp la sprite
     * @param e l'entite
     */
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
