package model.entites.FruitsPac;

import model.entites.Type;

public class NewFruit extends Type {
    int points;

    public void setPoints(int points){
        this.points = points;
    }

    @Override
    public int getPoints(){
        return points;
    }
}
