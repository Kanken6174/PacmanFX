package model.mouvement;

import model.entites.Entite;

public class Deplaceur {
    private Navigateur navigateur;

    public void deplacerEntiteVers(Entite source, Entite cible){
        navigateur.donneDirectionAPrendre(cible.getX(), cible.getY());
        //faire déplacer source dans direction de cible
    }
}
