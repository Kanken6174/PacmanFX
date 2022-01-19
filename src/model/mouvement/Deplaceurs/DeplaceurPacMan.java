package model.mouvement.Deplaceurs;

import javafx.application.Platform;
import model.Events.EventEmitter;
import model.Events.Events.EndGameEvent;
import model.Events.Events.ScoreObjectEatenEvent;
import model.entites.PacmanObject;
import model.entites.Type;
import model.enums.Orients;
import model.mouvement.Positions.PositionGraphique;
import model.mouvement.Positions.PositionLogique;
import model.terrain.Case;
import model.terrain.EspaceDeJeu;

public class DeplaceurPacMan extends Deplaceur {

    public DeplaceurPacMan(EspaceDeJeu EJ, PacmanObject aGerer, EventEmitter em) {
        super(EJ, aGerer,em);
    }

    @Override
    protected Case deplacement(){
        PositionLogique Posl = geree.getPositionLogique();  //quelle case
        PositionGraphique Posg = geree.getPositionGraphique();  //offset de -4 à 4
        //System.out.println("Entite a "+Posg.getx()+" , "+Posg.gety()+" | "+Posl.getCaseX()+" , "+Posl.getCaseY()+" | "+Posl.getOrient().toString());
        Orients DirectionVoulue =  Posl.getOrient();
        if(Posg.getx() > 4 || Posg.getx() < -4 || Posg.gety() > 4 || Posg.gety() < -4){
            Case Destination = EJ.getCardinals(Posl).get(DirectionVoulue.ordinal()); //on teste que à droite pour le moment...
            if(Destination == null || Destination.isObstacle()) {
                //System.out.println("destination innateignable "+ ((Destination == null) ? "null" : "obstacle"));
                return null;
            }else {
                PositionLogique pol = geree.getPositionLogique();
                Destination.ReceiveEntity(EJ.getStage()[pol.getCaseX()][pol.getCaseY()].passEntity(geree));
                if(Destination.hasGhosts()) //game over
                {
                    Platform.exit();
                }
                if(Destination.hasStaticEntities()){//mange une gomme
                    Destination.setEntiteStatique(null);
                }
            }
            switch (DirectionVoulue){
                case DROITE:
                    if(Posl.getCaseY() >= EJ.getMaxY())
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
                    if(Posl.getCaseX() >= EJ.getMaxX())
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
            super.em.setLocalEvent(new ScoreObjectEatenEvent((Type)locale.getStaticEntite()));
            locale.setEntiteStatique(null);
            super.em.sendEvent();
        }
        if(locale.hasGhosts()){
            super.em.setLocalEvent(new EndGameEvent());
            super.em.sendEvent();
        }
    }
}
