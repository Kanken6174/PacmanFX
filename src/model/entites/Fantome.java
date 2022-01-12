package model.entites;

import model.enums.FantomeNom;
import model.enums.FantomeState;
import model.enums.Orients;
import model.mouvement.ChasseComportement;
import model.mouvement.Positions.PositionLogique;
import model.mouvement.ScatterComportement;
import views.Sprites.SpriteAnimable;

public class Fantome extends Entite{
    public int spriteX = 441;   //définit la position du sprite sur la palette (X)
    public int spriteY = 49;    //début du sprite des fantomes  (Y)

    private PositionLogique scatterHome;
    private PositionLogique target;

    public Orients dircetionYeux; //La direction des yeux indique la direction du fantome

    private SpriteAnimable sa;

    public FantomeNom identifier;     //définit spriteY et de quel fantome il s'agit
    private FantomeState fs = FantomeState.SCATTER;

    private PacmanObject p;

    private ChasseComportement hunt;

    private ScatterComportement scat;

    //private Orients wantedDirection = Orients.HAUT;
    /**
     *
     * @param fn l'id du fantome, de 0 à 3 (enum)
     */

    public Fantome(FantomeNom fn, PacmanObject target){
        identifier = fn;
        spriteY += 16*identifier.ordinal();    //0-3
        p = target;
    }

    public Fantome(FantomeNom fn){
        identifier = fn;
        spriteY += 16*identifier.ordinal();    //0-3
    }

    public FantomeNom getFantomeNom(){
        return identifier;
    }

    public void setSpriteAnimable(SpriteAnimable sa){
        this.sa = sa;
    }

    public SpriteAnimable getSpriteAnimable(){
        return this.sa;
    }


    public void chasse() { hunt.chasse(p, this); }

    public void scatter() { scat.scatter(scatterHome, this); }
}
