package model.mouvement.Positions;

import model.enums.Orients;

public class PositionLogique {
    private int casex = 0;
    private int caseY = 0;
    private Orients Orient = Orients.DROITE;    //on utilise un enum, DROITE = 0, GAUCHE = 1, HAUT = 2, BAS = 3; aussi utilise pour les sprites

    public int getCasex() {
        return casex;
    }

    public int getCaseY(){
        return caseY;
    }

    public int getOrientInt(){
        return Orient.ordinal();
    }

    public Orients getOrient(){
        return Orient;
    }
}
