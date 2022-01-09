package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import model.entites.PacmanObject;
import model.terrain.EspaceDeJeu;
import views.gameView;

public class GameController implements EventHandler<KeyEvent> {

    @FXML private gameView gv;

    private EspaceDeJeu EJ;

    public GameController(gameView view){
        gv = view;
        EJ = new EspaceDeJeu();
        EJ.LoadStage("level");
        gv.DrawPlayspace(EJ.assemblePlayspace());
        gv.bindPacman(EJ.getPacman());
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()){
            case UP:
                PacmanObject pac = EJ.getPacman();
                pac.setPacAngle(270);
                break;
            case DOWN:
                EJ.getPacman().setPacAngle(90);
                break;
            case LEFT:
                EJ.getPacman().setPacAngle(180);
                break;
            case RIGHT:
                EJ.getPacman().setPacAngle(0);
                break;
            default:
                break;
        }
    }
}
