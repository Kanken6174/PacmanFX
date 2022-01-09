package views.Dessinateurs;

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

        toDraw.setX((entity.getLogicX()*10)+entity.getX());
        toDraw.setY((entity.getLogicY()*10)+entity.getY());
    }

    @Override
    public void run() {
        dessineEntite();
    }
}