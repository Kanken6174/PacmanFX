package model.mouvement.Deplaceurs;

import model.entites.Entite;
import model.mouvement.Navigateurs.Navigateur;
import model.terrain.EspaceDeJeu;

public class DeplaceurFantome extends Deplaceur {

    public DeplaceurFantome(EspaceDeJeu EJ, Entite aGerer) {
        super(EJ, aGerer);
    }

    @Override protected void deplacerEntite(){

    }



    @Override public void run(){
        deplacerEntite();
    }
}
