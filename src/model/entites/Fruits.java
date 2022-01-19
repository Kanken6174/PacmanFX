package model.entites;

public class Fruits extends Type{
    public String type;
    Type t;
    int points;

    private int getScore(Type t){
        return t.getPoints();
    }
}