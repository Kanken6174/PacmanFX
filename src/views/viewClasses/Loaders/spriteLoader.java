package views.viewClasses.Loaders;

import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;
import model.terrain.Case;
import tools.ImageRotate;
import views.viewClasses.Sprites.Sprite;

import java.nio.ByteBuffer;

public class spriteLoader {

    public static Sprite[][] makeCellSprites(Case[][] cases, int maxX, int maxY){
        ImageMaster im = new ImageMaster();
        Sprite[][] sprites = new Sprite[maxX][maxY];

        for(int x = 0; x < maxX; x++){
            for(int y = 0; y < maxY; y++){
                if(cases[x][y] != null) {
                     sprites[x][y] = getSpriteForCell(cases[x][y],
                            ((y+1 < maxY) ? cases[x][y+1] : null),
                            ((y-1 > 0) ? cases[x][y-1] : null),
                            ((x+1 < maxX) ? cases[x+1][y] : null),
                            (x-1 > 0) ? cases[x-1][y] : null,
                            ((y+1 > maxY && x+1 < maxX) ? cases[x+1][y+1] : null),
                            ((y+1 < maxY && x-1 > 0) ? cases[x-1][y+1] : null),
                            ((x+1 < maxX && y-1 > 0) ? cases[x+1][y-1] : null),
                            (x-1 > 0 && y-1 > 0) ? cases[x-1][y-1] : null,
                            im);
                }
            }
        }
        return sprites;
    }

    private static Sprite getSpriteForCell(Case toSkin, Case Top, Case Bottom, Case Left, Case Right, Case TopRight, Case TopLeft, Case BtRight, Case Btleft,ImageMaster im){
        Sprite sp = null;
        if(!toSkin.isObstacle()){
            if(toSkin.isGhostHouseDoor()){
                sp = new Sprite(ImageRotate.RotateDegx90(im.getTerrainTiles(13,12),90));  //porte de la maison à fantômes
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
                    sp = new Sprite(ImageRotate.RotateDegx90(im.getTerrainTiles(2,2),0));
                }else if(!TL){
                    sp = new Sprite(ImageRotate.RotateDegx90(im.getTerrainTiles(2,2),90));
                }else if(!BR){
                    sp = new Sprite(ImageRotate.RotateDegx90(im.getTerrainTiles(2,2),270));
                }else if(!BL){
                    sp = new Sprite(ImageRotate.RotateDegx90(im.getTerrainTiles(2,2),180));
                }else{
                    sp = new Sprite(im.getTerrainTiles(0,10));  //plein -> vide
                }
            }else if(T && B && R){
                sp = new Sprite(ImageRotate.RotateDegx90(im.getTerrainTiles(2,3),180));
            }else if(T && B && L){
                sp = new Sprite(ImageRotate.RotateDegx90(im.getTerrainTiles(2,3),0));
            }else if(L && B && R){
                sp = new Sprite(ImageRotate.RotateDegx90(im.getTerrainTiles(2,3),90));
            }else if(L && T && R){
                sp = new Sprite(ImageRotate.RotateDegx90(im.getTerrainTiles(2,3),270));
            }else if(B && R){
                sp = new Sprite(ImageRotate.RotateDegx90(im.getTerrainTiles(2,2),180));
            }else if(B && L){
                sp = new Sprite(ImageRotate.RotateDegx90(im.getTerrainTiles(2,2),270));
            }else if(T && L){
                sp = new Sprite(ImageRotate.RotateDegx90(im.getTerrainTiles(2,2),0));
            }else if(T && R){
                sp = new Sprite(ImageRotate.RotateDegx90(im.getTerrainTiles(2,2),90));
            }else{
                sp = new Sprite(im.getTerrainTiles(0,10));  //les autres ne devraient pas se produire, donc vide
            }
        }
        return sp;
    }

    public static WritableImage assemblePlayspace(Sprite[][] sprites, int maxX, int maxY){
        WritableImage[][] bis = new WritableImage[maxX][maxY];
        int pixelsX = maxX*8;
        int pixelsY = maxY*8;
        for(int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                if(sprites[x][y] != null){
                    bis[x][y] = sprites[x][y].getImage();
                }
            }
        }

        WritableImage playspace = new WritableImage(pixelsX,pixelsY);
        PixelWriter pw = playspace.getPixelWriter();


        WritablePixelFormat<ByteBuffer> pixelFormat = WritablePixelFormat.getByteBgraInstance();
        byte[] pixelBuffer = new byte[8*8];

        for(int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                if(bis[x][y] != null){
                    int xpixels = (int)bis[x][y].getWidth();
                    int ypixels = (int)bis[x][y].getHeight();

                    PixelReader pr = bis[x][y].getPixelReader();

                    for(int xbis = 0; xbis < xpixels; xbis++){
                        for(int ybis = 0; ybis < ypixels; ybis++){
                            Color cbis;
                            if(xbis == 0 && ybis == 0)
                                cbis = Color.BLUE;
                            else
                                cbis = pr.getColor(xbis,ybis);
                            pw.setColor(xbis+(x*xpixels),ybis+(y*ypixels),cbis);
                        }
                    }


                }
            }
        }

        return playspace;
    }
}
