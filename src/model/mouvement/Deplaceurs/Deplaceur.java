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
        if(Posg.getx() >= 4){
            Case Destination = EJ.getCardinals(Posl).get(Orients.DROITE.ordinal()); //on teste que à droite pour le moment...
            if(Destination == null || Destination.isObstacle())
                return;
            Destination.ReceiveEntity(EJ.getStage()[Posl.getCaseX()][Posl.getCaseY()].passEntity(geree));
        }else{
            Posg.setx(Posg.getx()+1);
        }
    }

    protected void passeCase(Case source, Case destination){
        destination.ReceiveEntity(source.passEntity(geree));
    }

    public void run(){
        deplacerEntite();
    }
}