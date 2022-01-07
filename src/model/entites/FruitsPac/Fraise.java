package model.entites.FruitsPac;

import model.entites.Type;

public class Fraise extends Type {
    int points = 300;

    @Override
    public int getPoints(){
        return points;
    }
}
