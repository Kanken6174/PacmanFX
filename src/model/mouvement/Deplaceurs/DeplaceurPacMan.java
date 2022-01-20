/**@author Yorick Geoffre
 * @brief Ce fichier contient les sources du Deplaceur d'entités pour Fantome*/

package model.mouvement.Deplaceurs;

import model.Events.EventEmitter;
import model.Events.Events.PacmanDeathEvent;
import model.Events.Events.ScoreObjectEatenEvent;
import model.entites.Mangeable;
import model.entites.Pacman;
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
        Orients DirectionVoulue =  Posl.getOrient();            //l'orientation de l'entité
        if(Posg.getx() > 4 || Posg.getx() < -4 || Posg.gety() > 4 || Posg.gety() < -4){
            Case Destination = EJ.getCardinals(Posl).get(DirectionVoulue.ordinal()); //on teste que à droite pour le moment...
            if(Destination == null || Destination.isObstacle()) {
                return null;
            }else {
                PositionLogique pol = geree.getPositionLogique();
                Destination.ReceiveEntity(EJ.getStage()[pol.getCaseRow()][pol.getCaseColumn()].passEntity(geree));
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
    protected void resolveEntityStates(Case locale) {
        if(locale.hasStaticEntities()){
            super.em.setLocalEvent(new ScoreObjectEatenEvent((Mangeable) locale.getStaticEntite()));
            locale.setEntiteStatique(null);
            super.em.sendEvent();
        }
        if(locale.hasGhosts()){
            super.em.setLocalEvent(new PacmanDeathEvent());
            super.em.sendEvent();
        }
    }
}
