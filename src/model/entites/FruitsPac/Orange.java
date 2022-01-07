package model.entites.FruitsPac;

import model.entites.Type;

public class Orange extends Type {
    int points = 500;

    @Override
    public int getPoints(){
        return points;
    }
}
