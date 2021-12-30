package model.graphics.Sprites;

import model.graphics.tools.ImageClipper;

import java.awt.image.BufferedImage;

public class SpriteAnimable extends Sprite {
    private BufferedImage Spritesheet;
    private int SpritesAmountCycle = 0; //nb de cycles différents dans le sprite
    private int SpritesAmountTypes = 0; //nb de types de cycles différents dans le sprite
    private int FrameWidth = 0;         //espace pris par chaque frame en largeur
    private int currentCycle = 0;       //définit laquelle des frames dy cycle est affichée (genre fantome qui regarde à droite, on a 2 frames)
    private int currentType = 0;        //définit quel type est affiché (genre fantome qui regarde à droite, gauche, haut, bas, on a 4 types)


    public SpriteAnimable(BufferedImage bi, int cycles, int types){ //ex: (imageDeFantome, 2 -> 2 frames par cycle, 4 -> 4 animations différentes dans l'image => 2*4 = 8 frames)
        Spritesheet = bi;
        SpritesAmountCycle = cycles;
        SpritesAmountTypes = types;
        FrameWidth = bi.getHeight();    //chaque sprite est soit 8*XX ou 16*XX, et sur une seule ligne, donc si on prend la hauteur on a la largeur de chaque frame
    }

    public BufferedImage getSpritesheet(){
        return Spritesheet;
    }

    public BufferedImage getFrame(){
        int frame = (FrameWidth*currentCycle);
        frame += currentType*(FrameWidth*SpritesAmountCycle);//on ajoute en décalage la taille d'une frame * le nb max de frames par cycle * l'index du type actuel
        return ImageClipper.getFrame(Spritesheet,(frame));  //ex (16*1) + ((16*4)*3) -> 2ème frame du fantôme qui regarde vers le haut
    }

    public int getSpritesAmountCycle(){
        return SpritesAmountCycle;
    }

    public int getSpritesAmountTypes(){
        return SpritesAmountTypes;
    }

    public void nextFrame(){
        if(currentCycle >= SpritesAmountCycle){
            currentCycle = 0;
        }else{
            currentCycle++;
        }
    }

    public void selectType(int selected){
        if(selected < 0 || selected > SpritesAmountTypes)
            selected = 0;
        currentType = selected;
    }
}
