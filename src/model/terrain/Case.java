package model.terrain;

import model.entites.Entite;

import java.util.ArrayList;

public class Case {
    private boolean estObstacle;
    private int SpriteIndex;
    private ArrayList<Entite> entites;

    public void setEstObstacle(Boolean b){
        estObstacle = b;
    }

    public boolean isObstacle(){
        return estObstacle;
    }

    public int getSpriteIndex(){
        return SpriteIndex;
    }

    public void ReceiveEntity(Entite e){
        entites.add(e);
    }

    public Entite passEntity(int index){    //"passe" l'entité à cet index à une autre case par exemple
        Entite e = entites.get(index);
        entites.remove(e);
        return e;
    }
}