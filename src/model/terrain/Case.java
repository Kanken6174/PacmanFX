package model.terrain;

import model.entites.Entite;

import java.util.ArrayList;

public class Case {
    private boolean estObstacle = true;
    private int SpriteIndex = -1;
    private ArrayList<Entite> Entites = new ArrayList<>();
    private Entite EntiteStatique = null;

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
        Entites.add(e);
    }

    public void ReceiveStaticEntity(Entite e){
        if(EntiteStatique == null)
            EntiteStatique = e;
    }

    public Entite passEntity(int index){    //"passe" l'entité à cet index à une autre case par exemple
        Entite e = Entites.get(index);
        Entites.remove(e);
        return e;
    }

    public Entite getEntite(int i){
        if(i > Entites.size())
            return null;
        return Entites.get(i);
    }

    public Entite getStaticEntite(){
        return EntiteStatique;
    }

    public boolean hasEntities(){
        return (Entites.size() > 0);
    }

    public boolean hasStaticEntities(){
        return (EntiteStatique != null);
    }
}