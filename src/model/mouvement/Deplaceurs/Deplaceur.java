package model.mouvement.Deplaceurs;

import model.entites.Entite;
import model.enums.Orients;
import model.mouvement.Navigateurs.Navigateur;

public abstract class Deplaceur {
    protected Navigateur navigateur;

    public void deplacerEntiteVers(Entite source, Entite cible, Orients direction){

    }
}