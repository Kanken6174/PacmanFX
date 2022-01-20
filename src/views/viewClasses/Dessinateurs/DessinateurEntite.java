package views.viewClasses.Dessinateurs;

import javafx.scene.image.ImageView;
import model.entites.Entite;

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