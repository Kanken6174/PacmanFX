/**@author Yorick Geoffre
 * @brief Ce fichier contient les sources du Deplaceur d'entités*/

package model.mouvement.Deplaceurs;

import model.Events.EventEmitter;
import model.Events.EventListener;
import model.Events.Events.Event;
import model.Events.Events.PacmanDeathEvent;
import model.boucles.Abonne;
import model.entites.Entite;
import model.mouvement.Positions.PositionLogique;
import model.terrain.Case;
import model.terrain.EspaceDeJeu;

/**
 * Le Deplaceur va gérer une entité dont il conserve une référence, et il sera abonné à une boucle de jeu.
 * A chaque "tick" de la boucle, il va chercher les cases valides pour l'entité gérée, déplacer cette entité, et
 * gérer les collisions qui ont survenu au déplacement.
 */
public abstract class Deplaceur implements Abonne, EventListener {
    /**Une référence à l'entité gérée*/
    protected Entite geree;
    /**Une référence à l'espace de jeu pour avoir accès aux cases et donc aux déplacements valides*/
    protected EspaceDeJeu EJ;
    /**Un émetteur d'évènement pour communiquer les conséquences du déplacement aux autres classes*/
    protected EventEmitter em;

    /**
     * Le constructeur du déplaceur
     * @param EJ Une référence à l'espace de jeu
     * @param aGerer Une référence à l'entité gérée
     * @param em Un émetteur d'évènement
     */
    public Deplaceur(EspaceDeJeu EJ, Entite aGerer, EventEmitter em){
        this.geree = aGerer;
        this.EJ = EJ;
        this.em = em;
    }

    /**Action appellée à chaque tick de la boucle de jeu, gère le déplacement, puis résout les conséquences.*/
    public void deplacerEntite(){
       Case destination = deplacement();
       if(destination != null)
           resolveEntityStates(destination);
    }
    /**La méthode qui gère le déplacement de l'entité gérée*/
    protected abstract Case deplacement();

    /**La méthode qui gère les conséquences d'un déplacement*
     * @param locale la Case vers laquelle l'entité a été déplacée
     */
    protected abstract void resolveEntityStates(Case locale);

    protected void resetPositionForManaged(){
        PositionLogique actual = geree.getPositionLogique();
        PositionLogique home = geree.getHome();

        Case Source = EJ.getCaseOrNull(actual);
        Case Destination = EJ.getCaseOrNull(home);

        Destination.ReceiveEntity(Source.passEntity(geree));

        geree.setLogicRow(home.getCaseRow());
        geree.setLogicColumn(home.getCaseColumn());
        geree.getPositionGraphique().sety(0);
        geree.getPositionGraphique().setx(0);
    }

    public void HandleEvent(Event e){
        if(e instanceof PacmanDeathEvent){
            resetPositionForManaged();
        }
    }

    @Override
    public void doAction(){
        deplacerEntite();
    }
}