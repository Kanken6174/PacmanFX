package model.entites;

public class Gomme extends Entite implements Mangeable{
    public final int points = 10;

    @Override
    public int getScore(){
        return points;
    }
}
