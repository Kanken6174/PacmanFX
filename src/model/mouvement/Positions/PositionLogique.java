/**@author Yorick Geoffre
 * @brief Ce fichier contient les sources de la PositionLogique*/

package model.mouvement.Positions;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.enums.Orients;

/**
 * La positionLogique est la position de l'entité (ou de la case) ciblée dans le double-tableau du terrain.
 * Elle permet de ne pas devoir parcourir tout le tableau à chaque fois que la position est nécessaire.
 */
public class PositionLogique {
    /**La position dans le tableau sur l'axe X*/
    private final IntegerProperty CaseX = new SimpleIntegerProperty();
        public int getCaseX() {return CaseX.get();}
        public void setCaseX(int value){this.CaseX.set(value);}
        public IntegerProperty CaseXProperty(){return CaseX;}
    /**La position dans le tableau sur l'axe Y*/
    private final IntegerProperty CaseY = new SimpleIntegerProperty();
        public int getCaseY() {return CaseY.get();}
        public void setCaseY(int value){this.CaseY.set(value);}
        public IntegerProperty CaseYProperty(){return CaseY;}

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
