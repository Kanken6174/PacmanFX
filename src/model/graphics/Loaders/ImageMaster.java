package model.graphics.Loaders;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import model.enums.FantomeNom;
import model.graphics.tools.ImageClipper;
import model.graphics.tools.ImageConverter;


public class ImageMaster {  //Cette classe contient la spritesheet principale et existe dans le manager
    private WritableImage MainRessource;

    public ImageMaster(){
       MainRessource = ImageConverter.ImageToWriteableImage(ImageLoader.loadRessources());
    }

    public ImageMaster(WritableImage MainRessource){
        this.MainRessource = MainRessource;
    }

    public Image getImageAt(int x, int y){
        Image img = ImageClipper.clipSprite(ImageLoader.loadRessources(),x,y);
        return img;
    }

    public WritableImage getSpritePart(int x, int y, int width, int height){
        WritableImage bi = null;
        try{
            bi = ImageClipper.clip(MainRessource,x,y,width,height);
        }
        catch (IndexOutOfBoundsException e){

        }
        return bi;
    }

    public WritableImage getTerrainTiles(int Xtile, int Ytile){
        int x = 228+(8*Xtile);//début des tiles de terrain à 228:0
        int y = 0+(8*Ytile);
        int height = 8;
        int width = 8;

        WritableImage bi = null;

        bi = getSpritePart(x,y,width,height);

        return bi;
    }

    public WritableImage getSpritesheetGhostFreightened(){
        int x = 456+(16*8);
        int y = 64;
        int height = 16;
        int width = 16*2;   //que 2 sprites pour celui-là

        WritableImage bi = null;

        bi = getSpritePart(x,y,width,height);

        return bi;
    }

    public WritableImage getSpritesheetForGhost(FantomeNom fn){
        int x = 456;
        int y = 64;
        int height = 16;
        int width = 16*8;

        y += fn.ordinal()*16;   //on récupère ce fantôme en particulier

        WritableImage bi = null;

        bi = getSpritePart(x,y,width,height);

        return bi;
    }
}