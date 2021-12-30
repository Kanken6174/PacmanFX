package model.entites;

import model.graphicsUtilities.Sprite;

public class Fantome extends Entite{
    public int spriteX = 441;   //définit la position du sprite sur la palette (X)
    public int spriteY = 49;    //début du sprite des fantomes  (Y)
    private int identifier;     //définit spriteY et de quel fantom il s'agit

    /**
     *
     * @param i l'id du fantome, de 0 à 3
     */
    public Fantome(int i){
        identifier = i;
        spriteY += 16*identifier;    //0-3 0 pour blinky 3 pour clyde
        sprite = new Sprite();
    }
}
