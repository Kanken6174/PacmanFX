package model.entites;

public class SuperGomme extends Gomme implements Mangeable{
    @Override
    public int getScore(){
        return 100;
    }
}