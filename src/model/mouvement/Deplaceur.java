package model.mouvement;

import model.entites.Entite;
import model.enums.Orients;

public class Deplaceur {
    private Navigateur navigateur;

    public void deplacerEntiteVers(Entite source, Entite cible, Orients direction){
        navigateur.donneDirectionAPrendre(source.getOrient(), cible.getLogicY(), cible.getLogicY());
        //faire déplacer source dans direction de cible
    }
}
