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
    /**La position dans le tableau sur l'axe Y*/
    private final IntegerProperty CaseRow = new SimpleIntegerProperty();
        public int getCaseRow() {return CaseRow.get();}
        public void setCaseRow(int value){this.CaseRow.set(value);}
        public IntegerProperty CaseRowProperty(){return CaseRow;}
    /**La position dans le tableau sur l'axe X*/
    private final IntegerProperty CaseColumn = new SimpleIntegerProperty();
        public int getCaseColumn() {return CaseColumn.get();}
        public void setCaseColumn(int value){this.CaseColumn.set(value);}
        public IntegerProperty CaseColProperty(){return CaseColumn;}

    private Orients Orient = Orients.DROITE;    //on utilise un enum, DROITE = 0, GAUCHE = 1, HAUT = 2, BAS = 3; aussi utilise pour les sprites

    /**
     * Le constructeur de la positionLogique
     * @param x la position dans le tableau sur l'axe X
     * @param y la position dans le tableau sur l'axe Y
     */
    public PositionLogique(int x, int y){
        setCaseRow(x);
        setCaseColumn(y);
    }

    /**
     * Change la direction logique contenue dans cette position
     * @param direction la nouvelle position logique
     */
    public void setOrient(Orients direction){
        Orient = direction;
    }

    public int getOrientInt(){
        return Orient.ordinal();
    }

    public Orients getOrient(){
        return Orient;
    }
}
