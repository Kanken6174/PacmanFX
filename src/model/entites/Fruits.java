package model.entites;

public class Fruits extends Type{
    public String type;
    Type t;
    int points = 1000;

    private int getScore(Type t){
        return t.getPoints();
    }
}