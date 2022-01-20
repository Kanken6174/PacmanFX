/**@author Yorick Geoffre
 * @brief Ce fichier contient les sources de la PositionGraphique*/

package model.mouvement.Positions;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Une PositionGraphique est un offset graphique entre 4 et -4 dans chaque direction et qui décrit la position de l'entité
 * relative à une Case du terrain (position logique)
 */
public class PositionGraphique {

    /**L'offset graphique sur l'axe X*/
    private final DoubleProperty x = new SimpleDoubleProperty();
        public double getx() {return x.get();}
        public void setx(double value){this.x.set(value);}
        public DoubleProperty xProperty(){return x;}

    /**L'offset graphique sur l'axe Y*/
    private final DoubleProperty y = new SimpleDoubleProperty();
        public double gety() {return y.get();}
        public void sety(double value){this.y.set(value);}
        public DoubleProperty yProperty(){return y;}

    /**L'offset graphique sur l'axe Z
     * @deprecated , cet axe était utilisé par l'ancien système de collisions et n'est plus requis*/
    private final DoubleProperty z = new SimpleDoubleProperty();
        public double getz() {return z.get();}
        public void setz(double value){this.z.set(value);}
        public DoubleProperty zProperty(){return z;}

    /**@deprecated
     * L'ancien constructeur qui initialisait également l'axe Z*/
    public PositionGraphique(double x, double y, double z){
        this.x.set(x);
        this.y.set(y);
        this.z.set(z);
    }

    /**
     * Le constructeur de la position graphique
     * @param x l'offset grahique sur l'axe X
     * @param y l'offset graphique sur l'axe Y
     */
    public PositionGraphique(double x, double y){
        this.x.set(x);
        this.y.set(y);
        this.z.set(0);
    }

    /**
     * Méthode de débogage
     * @return un string qui contient les informations de cette positionGraphique
     */
    public String print(){
        String toReturn = "X: " + Double.toString(x.get());
        toReturn = toReturn + " Y : "+Double.toString(y.get())+ " Z : " + Double.toString(z.get());
        return toReturn;
    }
}
