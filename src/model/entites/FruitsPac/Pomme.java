package model.entites.FruitsPac;

import model.entites.Type;

public class Pomme extends Type {
    int points = 700;

    @Override
    public int getPoints(){
        return points;
    }
}
