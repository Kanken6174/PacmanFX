package model.mouvement.Deplaceurs;

import model.entites.Entite;
import model.enums.Orients;
import model.mouvement.Positions.PositionGraphique;
import model.mouvement.Positions.PositionLogique;
import model.terrain.Case;
import model.terrain.EspaceDeJeu;

public abstract class Deplaceur implements Runnable{
    protected Entite geree;
    protected EspaceDeJeu EJ;

    public Deplaceur(EspaceDeJeu EJ, Entite aGerer){
        this.geree = aGerer;
        this.EJ = EJ;
    }

    protected void deplacerEntite(){
        PositionLogique Posl = geree.getPositionLogique();  //quelle case
        PositionGraphique Posg = geree.getPositionGraphique();  //offset de -4 à 4

        Orients DirectionVoulue =  Posl.getOrient();

        if(Posg.getx() >= 4 || Posg.getx() <= -4 || Posg.gety() >= 4 || Posg.gety() <= -4){
            Case Destination = EJ.getCardinals(Posl).get(DirectionVoulue.ordinal()); //on teste que à droite pour le moment...
            if(Destination == null || Destination.isObstacle())
                return;
            Destination.ReceiveEntity(EJ.getStage()[Posl.getCaseX()][Posl.getCaseY()].passEntity(geree));
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
    }

    protected void passeCase(Case source, Case destination){
        destination.ReceiveEntity(source.passEntity(geree));
    }

    public void run(){
        deplacerEntite();
    }
}