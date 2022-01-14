package model.mouvement.Deplaceurs;

import model.entites.Entite;
import model.terrain.EspaceDeJeu;

public abstract class Deplaceur implements Runnable{
    protected Entite geree;
    protected EspaceDeJeu EJ;

    public Deplaceur(EspaceDeJeu EJ, Entite aGerer){
        this.geree = aGerer;
        this.EJ = EJ;
    }

    protected void deplacerEntite(){

    }

    public void run(){
        deplacerEntite();
    }
}