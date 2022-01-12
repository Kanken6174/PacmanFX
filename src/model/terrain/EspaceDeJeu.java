package model.terrain;

import model.entites.Entite;
import model.entites.Fantome;
import model.entites.PacmanObject;
import model.terrain.loaders.collisionLoader;
import model.terrain.loaders.entityLoader;

import java.util.ArrayList;


public class EspaceDeJeu {
    private int maxX = 13;
    private int maxY = 28;
    private Case[][] tiles = new Case[maxX][maxY];

    public EspaceDeJeu(){

    }

    public void LoadStage(String StageName){
        tiles = new Case[maxX][maxY];   //taille pacman original
        tiles = collisionLoader.loadCollisions(StageName, maxX, maxY);
        tiles = entityLoader.loadEntities(StageName, tiles,maxX, maxY);
    }

    public Case[][] getStage(){
        return this.tiles;
    }

    public PacmanObject getPacman(){
        for(int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                Case processed = tiles[x][y];
                if(processed == null){
                    System.out.println("null cell at:"+x+" "+y);

                }else if(processed.containsPacMan()){
                    System.out.println("pacman at: "+x+" "+y);
                    PacmanObject pac = (PacmanObject)processed.getEntite(processed.getPacmanIndex());
                    return pac;
                }
            }
        }
        return null;
    }

    public ArrayList<Fantome> getFantomes(){
        ArrayList<Fantome> fantomes = new ArrayList<Fantome>();
        for(int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                Case processed = tiles[x][y];
                if(processed == null){
                    System.out.println("null cell at:"+x+" "+y);
                }else if(processed.hasGhosts()){
                    System.out.println("ghost at: "+x+" "+y);
                    ArrayList<Integer> indexes = processed.getGhostIndexes();
                    for(int i : indexes)
                         fantomes.add((Fantome) processed.getEntite(i));
                    if(fantomes.size() == 4)
                        return fantomes;
                }
            }
        }
        return fantomes;
    }

    public ArrayList<Entite> getAllEntites(){
        ArrayList<Entite> entites = new ArrayList<Entite>();
        for(int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                Case processed = tiles[x][y];
                if(processed == null){
                    System.out.println("null cell at:"+x+" "+y);
                }else{
                    if(processed.hasEntities()){
                        System.out.println("Entity at: "+x+" "+y);
                        entites.addAll(processed.getAllEntite());
                    }
                    if(processed.hasStaticEntities()){
                        entites.add(processed.getStaticEntite());
                    }
                }
            }
        }
        return entites;
    }
}
