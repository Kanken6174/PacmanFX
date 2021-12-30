package model.entites;

import model.enums.Orients;
import model.mouvement.Positions.PositionGraphique;
import model.mouvement.Positions.PositionLogique;

public class Entite {
    public int id;
    protected PositionGraphique pos;
    protected PositionLogique posL;

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
