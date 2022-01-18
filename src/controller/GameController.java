package controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import model.Events.ConcreteEmitter;
import model.Events.EventEmitter;
import model.boucles.Abonne;
import model.boucles.GestionnaireBoucles;
import model.entites.Fantome;
import model.enums.Orients;
import model.mouvement.Deplaceurs.DeplaceurFantome;
import model.mouvement.Deplaceurs.DeplaceurPacMan;
import model.terrain.EspaceDeJeu;
import views.gameView;

import java.util.ArrayList;

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
        SetupLoops();
    }

    private void SetupLoops(){
        EventEmitter em = new ConcreteEmitter();
        em.addListener(gb);
        DeplaceurPacMan test = new DeplaceurPacMan(EJ,EJ.getPacman(),em);
        gb.scheduleLoop(test, 50);

        ArrayList<Fantome> fantomes =  EJ.getFantomes();
        for(Fantome f : fantomes){
            DeplaceurFantome df = new DeplaceurFantome(EJ, f, em);
            gb.scheduleLoop(df,50);
        }

        gb.scheduleLoop(new Abonne(){@Override
        public void doAction() {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    gv.DrawCollisionMapDebug();
                }
            });
        }},50);
        gb.Start();
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()){
            case UP:
                EJ.getPacman().setPacAngle(-90);
                EJ.getPacman().getPositionLogique().setOrient(Orients.BAS);
                break;
            case DOWN:
                EJ.getPacman().setPacAngle(90);
                EJ.getPacman().getPositionLogique().setOrient(Orients.HAUT);
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
