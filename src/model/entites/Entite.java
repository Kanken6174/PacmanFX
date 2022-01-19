/**
 * @author Yorick geoffre
 * @brief Ce fichier contient les sources des entités
 */

package model.entites;

import model.enums.Orients;
import model.fileData.SpriteAnchor;
import model.mouvement.Positions.PositionGraphique;
import model.mouvement.Positions.PositionLogique;

/**
 * Une Entite représente un élément dont l'état peut changer, mobile ou non, comme le Pacman, les fantomes ou les gommes...
 */
public abstract class Entite {
    /**La position Graphique (offset -4 4) de l'entité sur le terrain*/
    protected PositionGraphique pos = new PositionGraphique(0,0);
    /**La position logique X et Y de la case contenant l'entité sur le terrain*/
    protected PositionLogique posL = new PositionLogique(0,0);
    /**Informations nécessaires pour récupérer le sprite de cette entité*/
    protected SpriteAnchor sp;

    public void setSpriteAnchor(SpriteAnchor sp){
        this.sp = sp;
    }

    public SpriteAnchor getSpriteAnchor(){
        return this.sp;
    }

    public double getGfxX(){
        return (int) pos.getx();
    }

    public double getGfxY(){
        return pos.gety();
    }

    public Orients getOrient(){
        return posL.getOrient();
    }

    public int getLogicX(){
        return posL.getCaseX();
    }

    public void setLogicX(int val) { posL.setCaseX(val); }

    public int getLogicY(){
        return posL.getCaseY();
    }

    public void setLogicY(int val) { posL.setCaseY(val); }

    public PositionGraphique getPositionGraphique() {return this.pos;}

    public PositionLogique getPositionLogique() {return this.posL;}
}
