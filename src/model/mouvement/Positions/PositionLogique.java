package model.mouvement.Positions;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.enums.Orients;

public class PositionLogique {
    private int casex = 0;

    private final IntegerProperty CaseX = new SimpleIntegerProperty();
        public int getCaseX() {return CaseX.get();}
        public void setCaseX(int value){this.CaseX.set(value);}
        public IntegerProperty CaseXProperty(){return CaseX;}

    private final IntegerProperty CaseY = new SimpleIntegerProperty();
        public int getCaseY() {return CaseY.get();}
        public void setCaseY(int value){this.CaseY.set(value);}
        public IntegerProperty CaseYProperty(){return CaseY;}

    private int caseY = 0;
    private Orients Orient = Orients.DROITE;    //on utilise un enum, DROITE = 0, GAUCHE = 1, HAUT = 2, BAS = 3; aussi utilise pour les sprites


    public int getOrientInt(){
        return Orient.ordinal();
    }

    public Orients getOrient(){
        return Orient;
    }
}
