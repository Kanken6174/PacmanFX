package model.mouvement.Deplaceurs;

import model.Events.EventEmitter;
import model.entites.Entite;
import model.enums.Orients;
import model.mouvement.Positions.PositionGraphique;
import model.mouvement.Positions.PositionLogique;
import model.terrain.Case;
import model.terrain.EspaceDeJeu;
import tools.OrientTools;

import java.util.ArrayList;

public class DeplaceurFantome extends Deplaceur{

    public DeplaceurFantome(EspaceDeJeu EJ, Entite aGerer, EventEmitter em) {
        super(EJ, aGerer, em);
    }

    @Override protected Case deplacement(){
        PositionLogique Posl = geree.getPositionLogique();  //quelle case
        PositionGraphique Posg = geree.getPositionGraphique();  //offset de -4 à 4

        Orients DirectionActuelle =  Posl.getOrient();
        Orients DirectionVoulue = Orients.HAUT;
        Orients DirectionInverse = OrientTools.invertOrient(DirectionActuelle);

        if(Posg.getx() > 4 || Posg.getx() < -4 || Posg.gety() > 4 || Posg.gety() < -4){
            ArrayList<Case> Cardinales = EJ.getCardinals(Posl);
            Case Destination = null;
            Case Precedente = Cardinales.get(DirectionInverse.ordinal());
            int index = 0;
            for(Case c : Cardinales){
                if(!(c == null || (c.isObstacle() && !c.isGhostHouseDoor()) && index!=DirectionInverse.ordinal())) {
                    Destination = c;
                    if(Cardinales.indexOf(c) == DirectionVoulue.ordinal())  //on la prend en priorité
                        break;
                }
                index++;
            }
            if(Destination == null)
                if(Precedente != null && !(Precedente.isObstacle() && !Precedente.isGhostHouseDoor()))
                    Destination = Precedente;
                else
                    return null;
            Case source = EJ.getStage()[Posl.getCaseX()][Posl.getCaseY()];
            if(source == null)
                return null;

            Destination.ReceiveEntity(source.passEntity(geree));

            switch (DirectionActuelle){
                case DROITE:
                    if(Posl.getCaseY() >= 28)
                        return null;
                    //Posl.setCaseX(Posl.getCaseX()-1);
                    Posl.setCaseY(Posl.getCaseY()+1);
                    Posg.setx(-4);
                    break;
                case GAUCHE:
                    if(Posl.getCaseY() <= 0)
                        return null;
                    //Posl.setCaseX(Posl.getCaseX()+1);
                    Posl.setCaseY(Posl.getCaseY()-1);
                    Posg.setx(4);
                    break;
                case HAUT:
                    if(Posl.getCaseX() >= 15)
                        return null;
                    Posg.sety(-4);
                    //Posl.setCaseY(Posl.getCaseY()-1);
                    Posl.setCaseX(Posl.getCaseX()+1);
                    break;
                case BAS:
                    if(Posl.getCaseX() <= 0)
                        return null;
                    Posg.sety(4);
                    //Posl.setCaseY(Posl.getCaseY()+1);
                    Posl.setCaseX(Posl.getCaseX()-1);
                    break;
                default:
                    break;
            }
            return Destination;
        }else{
            switch (DirectionActuelle){
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

    }


    @Override public void doAction(){
        deplacerEntite();
    }
}
