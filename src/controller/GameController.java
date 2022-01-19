/**
 * @author Yorick geoffre
 * @brief Ce fichier contient les sources du contrôleur de jeu
 */

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
import model.fileData.LevelFile;
import model.mouvement.Deplaceurs.DeplaceurFantome;
import model.mouvement.Deplaceurs.DeplaceurPacMan;
import model.terrain.EspaceDeJeu;
import views.GameView;

import java.util.ArrayList;

/**
 * Le GameController est le contrôleur qui va instancier l'espace de jeu et contrôller la GameView en appellant ses fonctions.
 * Il implémente EventHandler<KeyEvent> et gère les entrées du clavier de l'utilisateur effectuées sur la vue qu'il contrôle
 */
public class GameController implements EventHandler<KeyEvent> {

    @FXML private GameView gv;

    private EspaceDeJeu EJ;
    private GestionnaireBoucles gb = new GestionnaireBoucles();

    /**
     * Le constructeur de débogage du gameController (sans LevelFile)
     * @param view la vue à contrôler
     */
    public GameController(GameView view){
        gv = view;
        EJ = new EspaceDeJeu();
        EJ.LoadStage("level");
        gv.loadRessources(EJ);
        gv.DrawEntities(gb);
        SetupLoops();
    }

    /**
     * Le constructeur du gameController
     * @param view la vue à contrôler
     * @param lf le descripteur du fichier de niveau à charger
     */
    public GameController(GameView view, LevelFile lf){
        gv = view;
        EJ = new EspaceDeJeu();
        EJ.LoadStage(lf.getFilename(), lf.getColumnAmount(), lf.getRowAmount());
        gv.loadRessources(EJ);
        gv.DrawEntities(gb);
        SetupLoops();
    }

    /**
     * Met en place les différentes boucles de jeu en instanciant le GestionnaireBoucles local
     */
    private void SetupLoops(){
        EventEmitter em = new ConcreteEmitter();
        em.addListener(gb);
        DeplaceurPacMan test = new DeplaceurPacMan(EJ,EJ.getPacman(),em);
        gb.scheduleLoop(test, 10);

        ArrayList<Fantome> fantomes =  EJ.getFantomes();
        for(Fantome f : fantomes){
            DeplaceurFantome df = new DeplaceurFantome(EJ, f, em);
            gb.scheduleLoop(df,10);
        }

        gb.scheduleLoop(new Abonne(){@Override
        public void doAction() {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    gv.DrawCollisionMapDebug();
                }
            });
        }},10);
        gb.Start();
    }

    @Override
    /**
     * Va gérer les entrées du clavier de l'utilisateur
     * @param event l'évenement de clavier survenu
     */
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
