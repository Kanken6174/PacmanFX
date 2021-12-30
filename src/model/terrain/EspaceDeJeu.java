package model.terrain;

import model.terrain.loaders.collisionLoader;
import model.terrain.loaders.entityLoader;
import model.terrain.loaders.spriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

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

    public BufferedImage assemblePlayspace(){
        BufferedImage[][] bis = new BufferedImage[maxX][maxY];
        int pixelsX = maxX*8;
        int pixelsY = maxY*8;
        for(int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                if(tiles[x][y] != null){
                   bis[x][y] = tiles[x][y].getSprite().getImage();
                }
            }
        }

        BufferedImage playspace = new BufferedImage(pixelsY,pixelsX,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = playspace.createGraphics();

        for(int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                if(bis[x][y] != null){
                    g2d.drawImage(bis[x][y],y*8,x*8,null);
                }
            }
        }
        g2d.rotate(Math.PI/2);
        g2d.dispose();
        return playspace;
    }

    public Case[][] getStage(){
        return this.tiles;
    }
}
