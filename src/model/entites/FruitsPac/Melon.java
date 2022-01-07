package model.entites.FruitsPac;

import model.entites.Type;

public class Melon extends Type {
    int points = 1000;

    @Override
    public int getPoints(){
        return points;
    }
}
