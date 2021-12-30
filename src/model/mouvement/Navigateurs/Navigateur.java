package model.mouvement.Navigateurs;

import model.enums.Orients;

public abstract class Navigateur {

    public Orients donneDirectionAPrendre(Orients actuel, int caseX, int CaseY){
        int direction = 0;
        return Orients.values()[0];
    }
}