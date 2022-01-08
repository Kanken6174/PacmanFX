package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import model.terrain.EspaceDeJeu;
import views.gameView;

public class GameController implements EventHandler<KeyEvent> {

    @FXML private gameView gv;

    private EspaceDeJeu EJ;

    public GameController(gameView view){
        gv = view;
        EJ = new EspaceDeJeu();
        EJ.LoadStage("level");
        WritableImage playspaceBackground = EJ.assemblePlayspace();
        gv.DrawPlayspaceBackground(playspaceBackground);
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()){
            case UP:
                break;
            case DOWN:
                break;
            case LEFT:
                break;
            case RIGHT:
                break;
            default:
                break;
        }
    }
}
