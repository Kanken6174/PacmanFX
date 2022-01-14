package model.mouvement.Navigateurs;

import model.entites.Entite;
import model.enums.Orients;
import model.terrain.Case;
import model.terrain.EspaceDeJeu;

import java.util.ArrayList;

public abstract class Navigateur {

    public Orients donneDirectionAPrendre(Entite e, EspaceDeJeu ej){
        int direction = 0;
        ArrayList<Case> casesCardinales= ej.getCardinals(e.getPositionLogique());

        return Orients.values()[0];
    }
}