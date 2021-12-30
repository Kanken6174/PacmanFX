package model.entites;

import model.enums.FantomeNom;
import model.enums.FantomeState;
import model.graphics.Sprites.SpriteAnimable;

public class Fantome extends Entite{
    public int spriteX = 441;   //définit la position du sprite sur la palette (X)
    public int spriteY = 49;    //début du sprite des fantomes  (Y)

    private SpriteAnimable sa;

    private FantomeNom identifier;     //définit spriteY et de quel fantome il s'agit
    private FantomeState fs = FantomeState.SCATTER;
    /**
     *
     * @param fn l'id du fantome, de 0 à 3 (enum)
     */
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
}
