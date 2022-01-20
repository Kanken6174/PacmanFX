package model.entites;

import model.entites.FruitsPac.Galboss;

public class Fruits extends Entite implements Mangeable{
    public String type;
    Type t = new Galboss();

    public int getScore(){
        return t.getPoints();
    }
}