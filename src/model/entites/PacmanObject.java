package model.entites;

import javafx.beans.property.*;
import javafx.scene.input.KeyCode;

public class PacmanObject extends Entite{

    private DoubleProperty pacAngle = new SimpleDoubleProperty();
        public double getPacAngle(){return pacAngle.get();}
        public void setPacAngle(int value){pacAngle.set(value);}
        public DoubleProperty pacAngleProperty(){return pacAngle;}

    private DoubleProperty pacX = new SimpleDoubleProperty();
        public double getPacX(){return pacX.get();}
        public void setPacX(double value){pacX.set(value);}
        public DoubleProperty pacXProperty(){return pacX;}

    private DoubleProperty pacY = new SimpleDoubleProperty();
        public double getPacY(){return pacY.get();}
        public void setPacY(double value){pacY.set(value);}
        public DoubleProperty pacYProperty(){return pacY;}


    public void manger() {
        int fanomeMange = 0;
    }


    public void changePacOrient(KeyCode kc){
        switch (kc){
            case RIGHT:
                pacAngle.set(90);
                System.out.println("RIGHT");
                break;
            case UP:
                pacAngle.set(180);
                System.out.println("UP");
                break;
            case DOWN:
                pacAngle.set(0);
                System.out.println("DOWN");
                break;
            case LEFT:
                pacAngle.set(270);
                System.out.println("LEFT");
                break;
            default:
                break;
        }
    }
}
