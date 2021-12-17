package model.entites;

import model.CollisionSquare;
import model.Position;
import model.Sprite;

public class Entite {
    public int id;
    private Position pos;
    private CollisionSquare hitBox;
    private Sprite sprite;
    public int spriteX = 0;//définit la position du sprite sur la palette
    public int spriteY = 0;

    public int getX(){
        return (int) pos.x;
    }

    public int getY(){
        return (int) pos.y;
    }
}
