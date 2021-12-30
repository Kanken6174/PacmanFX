package model.graphics.Loaders;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import model.enums.FantomeNom;
import model.graphics.tools.ImageClipper;

import java.awt.image.BufferedImage;

public class ImageMaster {  //Cette classe contient la spritesheet principale et existe dans le manager
    private BufferedImage MainRessource;

    public ImageMaster(){
       MainRessource = ImageLoader.loadRessources();
    }

    public ImageMaster(BufferedImage MainRessource){
        this.MainRessource = MainRessource;
    }

    public Image getImageAt(int x, int y){
        Image img = SwingFXUtils.toFXImage(ImageClipper.clipSprite(ImageLoader.loadRessources(),x,y),null);
        return img;
    }

    public BufferedImage getSpritePart(int x, int y, int width, int height){
        BufferedImage bi = null;
        try{
            bi = MainRessource.getSubimage(x,y,width,height);
        }
        catch (IndexOutOfBoundsException e){

        }
        return bi;
    }

    public BufferedImage getTerrainTiles(int Xtile, int Ytile){
        int x = 228+(8*Xtile);//début des tiles de terrain à 228:0
        int y = 0+(8*Ytile);
        int height = 8;
        int width = 8;

        BufferedImage bi = null;

        bi = getSpritePart(x,y,width,height);

        return bi;
    }

    public BufferedImage getSpritesheetGhostFreightened(){
        int x = 456+(16*8);
        int y = 64;
        int height = 16;
        int width = 16*2;   //que 2 sprites pour celui-là

        BufferedImage bi = null;

        bi = getSpritePart(x,y,width,height);

        return bi;
    }

    public BufferedImage getSpritesheetForGhost(FantomeNom fn){
        int x = 456;
        int y = 64;
        int height = 16;
        int width = 16*8;

        y += fn.ordinal()*16;   //on récupère ce fantôme en particulier

        BufferedImage bi = null;

        bi = getSpritePart(x,y,width,height);

        return bi;
    }
}