package model.mouvement.Positions;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class PositionGraphique {
    private final DoubleProperty x = new SimpleDoubleProperty();
        public double getx() {return x.get();}
        public void setx(double value){this.x.set(value);}
        public DoubleProperty xProperty(){return x;}

    private final DoubleProperty y = new SimpleDoubleProperty();
        public double gety() {return y.get();}
        public void sety(double value){this.y.set(value);}
        public DoubleProperty yProperty(){return y;}

    private final DoubleProperty z = new SimpleDoubleProperty();
        public double getz() {return z.get();}
        public void setz(double value){this.z.set(value);}
        public DoubleProperty zProperty(){return z;}


    public PositionGraphique(double x, double y, double z){
        this.x.set(x);
        this.y.set(y);
        this.z.set(z);
    }

    public PositionGraphique(double x, double y){
        this.x.set(x);
        this.y.set(y);
        this.z.set(0);
    }

    public String print(){
        String toReturn = "X: " + Double.toString(x.get());
        toReturn = toReturn + " Y : "+Double.toString(y.get())+ " Z : " + Double.toString(z.get());
        return toReturn;
    }
}
