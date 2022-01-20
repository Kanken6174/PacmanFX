/**@author Yorick Geoffre
 * @brief Ce fichier contient les sources du Deplaceur d'entités pour Fantome*/

package model.mouvement.Deplaceurs;

import model.Events.EventEmitter;
import model.Events.Events.EndGameEvent;
import model.Events.Events.GhostEatenEvent;
import model.Events.Events.PacmanDeathEvent;
import model.Events.Events.ScoreObjectEatenEvent;
import model.entites.*;
import model.enums.Orients;
import model.mouvement.Positions.PositionGraphique;
import model.mouvement.Positions.PositionLogique;
import model.terrain.Case;
import model.terrain.EspaceDeJeu;

/**Ce Deplaceur est spécialisé pour le pacman*/
public class DeplaceurPacMan extends Deplaceur {

    public DeplaceurPacMan(EspaceDeJeu EJ, Pacman aGerer, EventEmitter em) {
        super(EJ, aGerer,em);
    }

    @Override
    protected Case deplacement(){
        PositionLogique Posl = geree.getPositionLogique();      //quelle case dans le double tableau du terrain
        PositionGraphique Posg = geree.getPositionGraphique();  //offset graphique de -4 à 4 par rapport à la case
        Case source = EJ.getCaseOrNull(Posl);
        if(source == null || source.isObstacle() || source.isGhostHouseDoor())
            super.resetPositionForManaged();
        Orients DirectionVoulue =  Posl.getOrient();            //l'orientation de l'entité
        if(Posg.getx() > 4 || Posg.getx() < -4 || Posg.gety() > 4 || Posg.gety() < -4){
            Case Destination = EJ.getCardinals(Posl).get(DirectionVoulue.ordinal()); //on teste que à droite pour le moment...
            if(Destination == null || Destination.isObstacle()) {
                PositionLogique dPos = EJ.getPoslPacmanDebug();
                if(dPos == null)    //ce cas est un interaction particulière où le pacman n'existe plus sur le terrain (dpos est sa position trouvée en parcourant le terrain)
                    super.resetPositionForManaged();    //on va donc reset le pacman pour se décoincer (observé quand on a la gomme de puissance et qu'on mange un fantome
                return null;                            //dans un coin
            }else {
                PositionLogique pol = geree.getPositionLogique();
                Destination.ReceiveEntity(source.passEntity(geree));
                geree.setLogicColumn(Destination.getPositionLog().getCaseColumn());
                geree.setLogicRow(Destination.getPositionLog().getCaseRow());
            }
            switch (DirectionVoulue){
                case DROITE:
                    if(Posl.getCaseColumn() >= EJ.getMaxY())
                        return null;
                    Posg.setx(-4);
                    Posg.sety(0);
                    break;
                case GAUCHE:
                    if(Posl.getCaseColumn() <= 0)
                        return null;
                    Posg.setx(4);
                    Posg.sety(0);
                    break;
                case HAUT:
                    if(Posl.getCaseRow() >= EJ.getMaxX())
                        return null;
                    Posg.sety(-4);
                    Posg.setx(0);
                    break;
                case BAS:
                    if(Posl.getCaseRow() <= 0)
                        return null;
                    Posg.sety(4);
                    Posg.setx(0);
                    break;
                default:
                    break;
            }
            return Destination;
        }else{
            switch (DirectionVoulue){
                case DROITE:
                    Posg.setx(Posg.getx()+1);
                    break;
                case GAUCHE:
                    Posg.setx(Posg.getx()-1);
                    break;
                case HAUT:
                    Posg.sety(Posg.gety()+1);
                    break;
                case BAS:
                    Posg.sety(Posg.gety()-1);
                    break;
                default:
                    break;
            }

        }
        return null;
    }

    @Override
    /**
     * Va gérer les intéractions du pacman avec sa nouvelle case, spécifiquement sa mort ou un mangeable mangé
     */
    protected void resolveEntityStates(Case locale) {
        if(locale.hasStaticEntities() && locale.getStaticEntite().isVisible()){
            locale.getStaticEntite().setVisible(false);
            super.em.setLocalEvent(new ScoreObjectEatenEvent((Mangeable) locale.getStaticEntite()));
            super.em.sendEvent();

            if(locale.getStaticEntite() instanceof SuperGomme){
                ((Pacman)geree).becomeSuper();
            }else if(locale.getStaticEntite() instanceof Gomme) {
                EJ.decrementPellets();
                if(EJ.getPelletsRemaining() <= 0) {
                    super.em.setLocalEvent(new EndGameEvent());
                    super.em.sendEvent();
                    return;
                }
            }
            locale.getStaticEntite().setVisible(false);
        }
        if(locale.hasGhosts()){
            if(((Pacman)geree).isSuper()){
                for(Fantome f : locale.getGhosts()) {
                    super.em.setLocalEvent(new GhostEatenEvent(f));
                    super.em.sendEvent();
                }
            }else {
                super.em.setLocalEvent(new PacmanDeathEvent());
                super.em.sendEvent();
            }
        }
    }
}
