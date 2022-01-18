package model.mouvement.Deplaceurs;

import model.Events.EventEmitter;
import model.boucles.Abonne;
import model.entites.Entite;
import model.terrain.Case;
import model.terrain.EspaceDeJeu;

public abstract class Deplaceur implements Abonne{
    protected Entite geree;
    protected EspaceDeJeu EJ;
    protected EventEmitter em;

    public Deplaceur(EspaceDeJeu EJ, Entite aGerer, EventEmitter em){
        this.geree = aGerer;
        this.EJ = EJ;
        this.em = em;
    }

    public void deplacerEntite(){
       Case destination = deplacement();
       if(destination != null)
           resolveEntityStates(destination);
    }

    protected abstract Case deplacement();

    protected abstract void resolveEntityStates(Case locale);

    protected void passeCase(Case source, Case destination){
        destination.ReceiveEntity(source.passEntity(geree));
    }

    @Override
    public void doAction(){
        deplacerEntite();
    }
}