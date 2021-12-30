package model.entites;

import model.collisions.HitboxSquare;
import model.enums.Orients;
import model.mouvement.PositionGraphique;
import model.graphicsUtilities.Sprite;
import model.mouvement.PositionLogique;

public class Entite {
    public int id;
    protected PositionGraphique pos;
    protected PositionLogique posL;
    protected HitboxSquare hitBox;
    protected Sprite sprite;

    public int getX(){
        return (int) pos.getX();
    }

    public float getY(){
        return pos.getY();
    }

    public Orients getOrient(){
        return posL.getOrient();
    }

    public int getLogicX(){
        return posL.getCasex();
    }

    public int getLogicY(){
        return posL.getCaseY();
    }
}
