package model.entites;

import model.enums.Orients;
import model.fileData.SpriteAnchor;
import model.mouvement.Positions.PositionGraphique;
import model.mouvement.Positions.PositionLogique;

public class Entite {
    public int id;
    protected PositionGraphique pos = new PositionGraphique(0,0);
    protected PositionLogique posL = new PositionLogique(0,0);
    protected SpriteAnchor sp;

    public void setAnchor(SpriteAnchor sp){
        this.sp = sp;
    }

    public SpriteAnchor getSp(){
        return this.sp;
    }

    public double getX(){
        return (int) pos.getx();
    }

    public double getY(){
        return pos.gety();
    }

    public Orients getOrient(){
        return posL.getOrient();
    }

    public int getLogicX(){
        return posL.getCaseX();
    }

    public int getLogicY(){
        return posL.getCaseY();
    }

    public PositionGraphique getPositionGraphique(){return this.pos;}

    public PositionLogique getPositionLogique(){return this.posL;}
}
