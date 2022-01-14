package model.mouvement.Positions;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.enums.Orients;

public class PositionLogique {

    private final IntegerProperty CaseX = new SimpleIntegerProperty();
        public int getCaseX() {return CaseX.get();}
        public void setCaseX(int value){this.CaseX.set(value); ScaledX.set(value*10);}
        public IntegerProperty CaseXProperty(){return CaseX;}

    private final IntegerProperty CaseY = new SimpleIntegerProperty();
        public int getCaseY() {return CaseY.get();}
        public void setCaseY(int value){this.CaseY.set(value); ScaledY.set(value*10);}
        public IntegerProperty CaseYProperty(){return CaseY;}

    private final IntegerProperty ScaledX = new SimpleIntegerProperty();
        public IntegerProperty ScaledXProperty(){return ScaledX;}

    private final IntegerProperty ScaledY = new SimpleIntegerProperty();
        public IntegerProperty ScaledYProperty(){return ScaledY;}

    private Orients Orient = Orients.DROITE;    //on utilise un enum, DROITE = 0, GAUCHE = 1, HAUT = 2, BAS = 3; aussi utilise pour les sprites

    public PositionLogique(int x, int y){
        setCaseX(x);
        setCaseY(y);
    }

    public void setOrient(Orients direction){
        Orient = direction;
    }

    public void forceUpdate(){
        CaseX.set(CaseX.get());
        CaseY.set(CaseY.get());
    }

    public int getOrientInt(){
        return Orient.ordinal();
    }

    public Orients getOrient(){
        return Orient;
    }
}
