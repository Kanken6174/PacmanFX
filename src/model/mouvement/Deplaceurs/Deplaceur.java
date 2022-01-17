package model.mouvement.Deplaceurs;

import model.boucles.Abonne;
import model.entites.Entite;
import model.enums.Orients;
import model.mouvement.Positions.PositionGraphique;
import model.mouvement.Positions.PositionLogique;
import model.terrain.Case;
import model.terrain.EspaceDeJeu;

public abstract class Deplaceur implements Abonne {
    protected Entite geree;
    protected EspaceDeJeu EJ;

    public Deplaceur(EspaceDeJeu EJ, Entite aGerer){
        this.geree = aGerer;
        this.EJ = EJ;
    }

    protected void deplacerEntite(){
        PositionLogique Posl = geree.getPositionLogique();  //quelle case
        PositionGraphique Posg = geree.getPositionGraphique();  //offset de -4 à 4
        System.out.println("Entite a "+Posg.getx()+" , "+Posg.gety()+" | "+Posl.getCaseX()+" , "+Posl.getCaseY()+" | "+Posl.getOrient().toString());
        Orients DirectionVoulue =  Posl.getOrient();
        if(Posg.getx() > 4 || Posg.getx() < -4 || Posg.gety() > 4 || Posg.gety() < -4){
            Case Destination = EJ.getCardinals(Posl).get(DirectionVoulue.ordinal()); //on teste que à droite pour le moment...
            if(Destination == null || Destination.isObstacle()) {
                System.out.println("destination innateignable "+ ((Destination == null) ? "null" : "obstacle"));
                return;
            }else {
                PositionLogique pol = EJ.getPoslPacmanDebug();
                Destination.ReceiveEntity(EJ.getStage()[pol.getCaseX()][pol.getCaseY()].passEntity(geree));
            }
                switch (DirectionVoulue){
                    case DROITE:
                        if(Posl.getCaseY() >= 28)
                            return;
                        //Posl.setCaseX(Posl.getCaseX()-1);
                        Posl.setCaseY(Posl.getCaseY()+1);
                        Posg.setx(-4);
                        break;
                    case GAUCHE:
                        if(Posl.getCaseY() <= 0)
                            return;
                        //Posl.setCaseX(Posl.getCaseX()+1);
                        Posl.setCaseY(Posl.getCaseY()-1);
                        Posg.setx(4);
                        break;
                    case HAUT:
                        if(Posl.getCaseX() >= 15)
                            return;
                        Posg.sety(-4);
                        //Posl.setCaseY(Posl.getCaseY()-1);
                        Posl.setCaseX(Posl.getCaseX()+1);
                        break;
                    case BAS:
                        if(Posl.getCaseX() <= 0)
                            return;
                        Posg.sety(4);
                        //Posl.setCaseY(Posl.getCaseY()+1);
                        Posl.setCaseX(Posl.getCaseX()-1);
                        break;
                    default:
                        break;
            }
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

    @Override
    public void doAction(){
        deplacerEntite();
    }
}