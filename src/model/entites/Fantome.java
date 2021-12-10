package model.entites;

public class Fantome extends Entite{
    public int spriteX = 441;//définit la position du sprite sur la palette
    public int spriteY = 49;//début du sprite des fantomes
    private int identifier;//définit spriteY

    /**
     *
     * @param i l'id du fantome, de 0 à 3
     */
    public Fantome(int i){
        spriteY += 16*i;    //0-3 0 pour blinky 3 pour clyde
    }
}
