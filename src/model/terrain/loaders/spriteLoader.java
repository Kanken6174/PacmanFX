package model.terrain.loaders;

import javafx.scene.image.WritableImage;
import model.entites.Fantome;
import model.enums.FantomeNom;
import model.graphics.Loaders.ImageMaster;
import model.graphics.Sprites.Sprite;
import model.graphics.Sprites.SpriteAnimable;
import model.graphics.tools.ImageRotate;
import model.terrain.Case;


import java.util.ArrayList;

public class spriteLoader {
    public static Case[][] loadSprites(Case[][] cases, int maxX, int maxY){
        ImageMaster im = new ImageMaster();
        WritableImage blinkySpritesheet = im.getSpritesheetForGhost(FantomeNom.BLINKY);


        for(int x = 0; x < maxX; x++){
            for(int y = 0; y < maxY; y++){
                if(cases[x][y] != null) {
                    setGhostSpritesForCell(cases[x][y], im);
                    skinCell(cases[x][y],
                            ((y+1 < maxY) ? cases[x][y+1] : null),
                            ((y-1 > 0) ? cases[x][y-1] : null),
                            ((x+1 < maxX) ? cases[x+1][y] : null),
                            (x-1 > 0) ? cases[x-1][y] : null,
                            ((y-1 < maxY && x+1 < maxX) ? cases[x+1][y+1] : null),
                            ((y+1 < maxY && x-1 > 0) ? cases[x-1][y+1] : null),
                            ((x+1 < maxX && y-1 > 0) ? cases[x+1][y-1] : null),
                            (x-1 > 0 && y-1 > 0) ? cases[x-1][y-1] : null,
                            im);
                }
            }
        }
        return cases;
    }

    private static void skinCell(Case toSkin, Case Top, Case Bottom, Case Left, Case Right, Case TopRight, Case TopLeft, Case BtRight, Case Btleft,ImageMaster im){
        Sprite sp = null;
        if(!toSkin.isObstacle()){
            if(toSkin.isGhostHouseDoor()){
                sp = new Sprite(im.getTerrainTiles(13,12));  //porte de la maison à fantômes
            }else{
                sp = new Sprite(im.getTerrainTiles(0,10));    //bloc vide (noir)
            }
        }else {
            boolean T = (Top == null || Top.isObstacle());
            boolean B = (Bottom == null || Bottom.isObstacle());    //cardinales
            boolean L = (Left == null || Left.isObstacle());
            boolean R = (Right == null || Right.isObstacle());

            boolean TR = (TopRight == null || TopRight.isObstacle());
            boolean TL = (TopLeft == null || TopLeft.isObstacle());
            boolean BR = (BtRight == null || BtRight.isObstacle()); //diagonales
            boolean BL = (Btleft == null || Btleft.isObstacle());

            if(T && B && R && L){
                if(!TR){
                    sp = new Sprite(ImageRotate.RotateDeg(im.getTerrainTiles(2,2),0));
                }else if(!TL){
                    sp = new Sprite(ImageRotate.RotateDeg(im.getTerrainTiles(2,2),270));
                }else if(!BR){
                    sp = new Sprite(ImageRotate.RotateDeg(im.getTerrainTiles(2,2),90));
                }else if(!BL){
                    sp = new Sprite(ImageRotate.RotateDeg(im.getTerrainTiles(2,2),180));
                }else{
                    sp = new Sprite(im.getTerrainTiles(0,10));  //plein -> vide
                }
            }else if(T && B && R){
                sp = new Sprite(ImageRotate.RotateDeg(im.getTerrainTiles(2,3),270));
            }else if(T && B && L){
                sp = new Sprite(ImageRotate.RotateDeg(im.getTerrainTiles(2,3),90));
            }else if(L && B && R){
                sp = new Sprite(ImageRotate.RotateDeg(im.getTerrainTiles(2,3),0));
            }else if(L && T && R){
                sp = new Sprite(ImageRotate.RotateDeg(im.getTerrainTiles(2,3),180));
            }else if(B && R){
                sp = new Sprite(ImageRotate.RotateDeg(im.getTerrainTiles(2,2),180));
            }else if(B && L){
                sp = new Sprite(ImageRotate.RotateDeg(im.getTerrainTiles(2,2),90));
            }else if(T && L){
                sp = new Sprite(ImageRotate.RotateDeg(im.getTerrainTiles(2,2),0));
            }else if(T && R){
                sp = new Sprite(ImageRotate.RotateDeg(im.getTerrainTiles(2,2),270));
            }else{
                sp = new Sprite(im.getTerrainTiles(0,10));  //les autres ne devraient pas se produire, donc vide
            }
        }
        toSkin.setSprite(sp);
    }

    private static void setGhostSpritesForCell(Case c, ImageMaster im){
        if (c.hasGhosts()) {
            ArrayList<Integer> indexsGhost = c.getGhostIndexes();
            for (Integer i : indexsGhost) {
                WritableImage bi = im.getSpritesheetForGhost(((Fantome) c.getEntite(i)).getFantomeNom()); //C'est toujours un fantome, le cast est requis
                                                                                                          //pour qu'il trouve la méthode getFantomeNom...
                SpriteAnimable sa = new SpriteAnimable(bi,2,4);
                ((Fantome) c.getEntite(i)).setSpriteAnimable(sa);
            }
        }
    }
}
