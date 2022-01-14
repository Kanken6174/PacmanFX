package model.terrain;

import model.entites.Entite;
import model.entites.Fantome;
import model.entites.PacmanObject;
import views.Sprites.Sprite;

import java.util.ArrayList;

public class Case {
    private boolean estObstacle = true;
    private boolean isGhostHouseDoor = false;
    private boolean containsPacMan = false;
    private int SpriteIndex = -1;       //inutilisé
    private Sprite sp;

    private ArrayList<Entite> Entites = new ArrayList<>();
    private Entite EntiteStatique = null;

    public void setEstObstacle(Boolean b){
        estObstacle = b;
    }
    public void setGhostHouseDoor(Boolean b){ isGhostHouseDoor = b;}

    public boolean isObstacle(){
        return estObstacle;
    }

    public boolean isGhostHouseDoor(){return isGhostHouseDoor;}

    public int getSpriteIndex(){
        return SpriteIndex;
    }

    public void ReceiveEntity(Entite e){
        Entites.add(e);
        if(e instanceof PacmanObject)
            containsPacMan = true;
    }

    public void ReceiveStaticEntity(Entite e){
        if(EntiteStatique == null)
            EntiteStatique = e;
    }

    public Entite passEntity(int index){    //"passe" l'entité à cet index à une autre case par exemple
        Entite e = Entites.get(index);
        Entites.remove(e);
        if(e instanceof PacmanObject)
            containsPacMan = false;
        return e;
    }

    public Entite passEntity(Entite e){
        if(Entites.contains(e)){
            Entites.remove(e);
            return e;
        }else{
            return null;
        }
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

    public boolean hasGhosts(){
        boolean toReturn = false;
        for(Entite e : Entites){
            if(e instanceof Fantome){
                toReturn = true;
                break;
            }

        }
        return toReturn;
    }

    public ArrayList<Entite> getAllEntite(){
        return this.Entites;
    }

    public ArrayList<Integer> getGhostIndexes(){
        ArrayList<Integer> indexs = new ArrayList<>();
        for(Entite e : Entites){
            if(e instanceof Fantome){
                indexs.add(Entites.indexOf(e));
            }
        }
        return indexs;
    }

    public int getPacmanIndex() {
        if (containsPacMan) {
            int i = 0;
            for (Entite e : Entites) {
                if (e instanceof PacmanObject) {
                    return i;
                }
                i++;
            }
        }
        return -1;  //cas d'erreur
    }

    public boolean hasStaticEntities(){
        return (EntiteStatique != null);
    }

    public boolean containsPacMan(){
        return containsPacMan;
    }

    public void setSprite(Sprite sp){
        this.sp = sp;
    }

    public Sprite getSprite(){
        return this.sp;
    }
}