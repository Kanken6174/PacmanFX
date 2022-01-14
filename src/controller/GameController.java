package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import model.boucles.GestionnaireBoucles;
import model.enums.Orients;
import model.mouvement.Deplaceurs.DeplaceurPacMan;
import model.terrain.EspaceDeJeu;
import views.gameView;

public class GameController implements EventHandler<KeyEvent> {

    @FXML private gameView gv;

    private EspaceDeJeu EJ;
    private GestionnaireBoucles gb = new GestionnaireBoucles();

    public GameController(gameView view){
        gv = view;
        EJ = new EspaceDeJeu();
        EJ.LoadStage("level");
        gv.loadRessources(EJ);
        gv.DrawEntities(gb);
        DeplaceurPacMan test = new DeplaceurPacMan(EJ.getPacman(),EJ);
        gb.scheduleLoop(test, 100);
        gb.Start();
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()){
            case UP:
                EJ.getPacman().setPacAngle(270);
                EJ.getPacman().getPositionLogique().setOrient(Orients.HAUT);
                break;
            case DOWN:
                EJ.getPacman().setPacAngle(90);
                EJ.getPacman().getPositionLogique().setOrient(Orients.BAS);
                break;
            case LEFT:
                EJ.getPacman().setPacAngle(180);
                EJ.getPacman().getPositionLogique().setOrient(Orients.GAUCHE);
                break;
            case RIGHT:
                EJ.getPacman().setPacAngle(0);
                EJ.getPacman().getPositionLogique().setOrient(Orients.DROITE);
                break;
            default:
                break;
        }
    }
}
