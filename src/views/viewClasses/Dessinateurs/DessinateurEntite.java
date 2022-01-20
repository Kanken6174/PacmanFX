/**@author Yorick Geoffre
 * @brief contient les sources du dessinateur d'entités
 */

package views.viewClasses.Dessinateurs;

import javafx.scene.image.ImageView;
import model.entites.Entite;

/**
 * @deprecated le dessinateur d'entite était sensé dessiner chaque entité en la plaçant sur la vue, il est rendu redondant
 * par les bindings dans les vues elle-mêmes
 */
public class DessinateurEntite implements Runnable {
    ImageView toDraw = null;
    Entite entity = null;

    public DessinateurEntite(ImageView img, Entite entite){
        this.toDraw = img;
        this.entity = entite;
    }

    private void dessineEntite(){
        if(entity == null){
            toDraw.setVisible(false);
            return;
        }

        toDraw.setX((entity.getLogicRow()*10)+entity.getGfxX());
        toDraw.setY((entity.getLogicColumn()*10)+entity.getGfxY());
    }

    @Override
    public void run() {
        dessineEntite();
    }
}