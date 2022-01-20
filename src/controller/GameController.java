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
import model.boucles.GestionnaireBoucles;
import model.entites.Fantome;
import model.entites.Pacman;
import model.enums.Orients;
import model.fileData.LevelFile;
import model.mouvement.Deplaceurs.DeplaceurFantome;
import model.mouvement.Deplaceurs.DeplaceurPacMan;
import model.partie.CompteurScore;
import model.partie.CompteurVie;
import model.terrain.EspaceDeJeu;
import views.GameView;

import java.util.ArrayList;

/**
 * Le GameController est le contrôleur qui va instancier l'espace de jeu et contrôller la GameView en appellant ses fonctions.
 * Il implémente EventHandler et gère les entrées du clavier de l'utilisateur effectuées sur la vue qu'il contrôle
 */
public class GameController implements EventHandler<KeyEvent> {

    @FXML private GameView gv;

    private EspaceDeJeu EJ;

    private CompteurScore cs;
    private CompteurVie cv;

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
        cs = new CompteurScore("lorem");
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
        ConcreteEmitter em = new ConcreteEmitter();
        cv = new CompteurVie(em);
        gv.bindCompteurs(cs,cv);
        em.addListener(gb);
        em.addListener(cs);
        em.addListener(cv);
        em.addListener(gv);
        DeplaceurPacMan dpac = new DeplaceurPacMan(EJ,EJ.getPacman(),em);
        em.addListener(dpac);
        gb.scheduleLoop(dpac, 10);

        ArrayList<Fantome> fantomes =  EJ.getFantomes();
        for(Fantome f : fantomes){
            DeplaceurFantome df = new DeplaceurFantome(EJ, f, em);
            em.addListener(df);
            gb.scheduleLoop(df,40);
        }
        if(false) //affichage de debug
            gb.scheduleLoop(() -> {Platform.runLater(() ->{gv.DrawCollisionMapDebug();});},10);

        gb.Start();
    }

    @Override
    /**
     * Va gérer les entrées du clavier de l'utilisateur
     * @param event l'évenement de clavier survenu
     */
    public void handle(KeyEvent event) {
        Pacman pac = EJ.getPacman();
        if(pac == null)
            return;
        switch (event.getCode()){
            case UP:
                pac.setPacAngle(-90);
                pac.getPositionLogique().setOrient(Orients.BAS);
                break;
            case DOWN:
                pac.setPacAngle(90);
                pac.getPositionLogique().setOrient(Orients.HAUT);
                break;
            case LEFT:
                pac.setPacAngle(180);
                pac.getPositionLogique().setOrient(Orients.GAUCHE);
                break;
            case RIGHT:
                pac.setPacAngle(0);
                pac.getPositionLogique().setOrient(Orients.DROITE);
                break;
            default:
                break;
        }
    }
}
