package model.mouvement.Deplaceurs;

import model.boucles.Abonne;
import model.entites.Entite;
import model.terrain.Case;
import model.terrain.EspaceDeJeu;

public abstract class Deplaceur implements Abonne {
    protected Entite geree;
    protected EspaceDeJeu EJ;

    public Deplaceur(EspaceDeJeu EJ, Entite aGerer){
        this.geree = aGerer;
        this.EJ = EJ;
    }

    protected abstract void deplacerEntite();

    protected void passeCase(Case source, Case destination){
        destination.ReceiveEntity(source.passEntity(geree));
    }

    @Override
    public void doAction(){
        deplacerEntite();
    }
}