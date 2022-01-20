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
    /**La position logique X et Y de la case d'origine de l'entité*/
    protected PositionLogique posHome = new PositionLogique(0,0);

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

    public int getLogicRow(){
        return posL.getCaseRow();
    }

    public void setLogicRow(int val) { posL.setCaseRow(val); }

    public int getLogicColumn(){
        return posL.getCaseColumn();
    }

    public void setLogicColumn(int val) { posL.setCaseColumn(val); }

    public void setHome(int row, int col){posHome.setCaseRow(row); posHome.setCaseColumn(col);}
    public PositionLogique getHome(){return this.posHome;}

    public PositionGraphique getPositionGraphique() {return this.pos;}

    public PositionLogique getPositionLogique() {return this.posL;}
}
