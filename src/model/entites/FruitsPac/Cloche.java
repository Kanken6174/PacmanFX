package model.entites.FruitsPac;

import model.entites.Type;

public class Cloche extends Type {
    int points = 2000;

    @Override
    public int getPoints(){
        return points;
    }
}
