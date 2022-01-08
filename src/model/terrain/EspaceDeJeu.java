package model.terrain;

import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;
import model.terrain.loaders.collisionLoader;
import model.terrain.loaders.entityLoader;
import model.terrain.loaders.spriteLoader;

import java.nio.ByteBuffer;


public class EspaceDeJeu {
    private Case[][] tiles;
    private int maxX = 13;
    private int maxY = 28;

    public EspaceDeJeu(){

    }

    public void LoadStage(String StageName){
        tiles = new Case[maxX][maxY];   //taille pacman original
        tiles = collisionLoader.loadCollisions(StageName, maxX, maxY);
        tiles = entityLoader.loadEntities(StageName, tiles,maxX, maxY);
        tiles = spriteLoader.loadSprites(tiles, maxX, maxY);
        assemblePlayspace();
    }

    public WritableImage assemblePlayspace(){
        WritableImage[][] bis = new WritableImage[maxX][maxY];
        int pixelsX = maxX*8;
        int pixelsY = maxY*8;
        for(int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                if(tiles[x][y] != null){
                   bis[x][y] = tiles[x][y].getSprite().getImage();
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
                            Color cbis = pr.getColor(xbis,ybis);
                            pw.setColor(xbis+(x*xpixels),ybis+(y*ypixels),cbis);
                        }
                    }


                }
            }
        }

        return playspace;
    }

    public Case[][] getStage(){
        return this.tiles;
    }
}
