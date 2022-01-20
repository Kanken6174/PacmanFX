package model.terrain;

import model.entites.Entite;
import model.entites.Fantome;
import model.entites.Pacman;
import model.mouvement.Positions.PositionLogique;
import model.partie.CompteurVie;
import model.terrain.loaders.CollisionLoader;
import model.terrain.loaders.entityLoader;

import java.util.ArrayList;


public class EspaceDeJeu {
    private int maxX = 15;  //lignes
    private int maxY = 28;  //colonnes
    private String levelName = "";
    private Case[][] tiles = new Case[maxX][maxY];
    private CompteurVie cv = new CompteurVie();

    public void LoadStage(String StageName){
        levelName = StageName;
        tiles = new Case[maxX][maxY];
        tiles = CollisionLoader.loadCollisions(StageName, maxX, maxY);
        tiles = entityLoader.loadEntities(StageName, tiles,maxX, maxY);
    }

    public void LoadStage(String StageName, int columns, int rows){
        maxX = rows;
        maxY = columns;
        levelName = StageName;
        tiles = new Case[maxX][maxY];
        tiles = CollisionLoader.loadCollisions(StageName, maxX, maxY);
        tiles = entityLoader.loadEntities(StageName, tiles,maxX, maxY);
    }

    public int getMaxX(){
        return maxX;
    }

    public int getMaxY(){
        return maxY;
    }

    public CompteurVie getCv(){
        return cv;
    }

    public void reset(){
        LoadStage(levelName);
    }

    public Case[][] getStage(){
        return this.tiles;
    }

    public Pacman getPacman(){
        for(int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                Case processed = tiles[x][y];
                if(processed != null && processed.containsPacMan()){
                    Pacman pac = (Pacman)processed.getEntite(processed.getPacmanIndex());
                    return pac;
                }
            }
        }
        return null;
    }

    public PositionLogique getPoslPacmanDebug(){
        PositionLogique pol = null;
        for(int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                Case processed = tiles[x][y];
                if(processed == null){
                    //System.out.println("null cell at:"+x+" "+y);

                }else if(processed.containsPacMan()){
                    pol = new PositionLogique(x,y);
                }
            }
        }
        return pol;
    }

    public ArrayList<Fantome> getFantomes(){
        ArrayList<Fantome> fantomes = new ArrayList<Fantome>();
        for(int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                Case processed = tiles[x][y];
                if(processed == null){
                    //System.out.println("null cell at:"+x+" "+y);
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
                    //System.out.println("null cell at:"+x+" "+y);
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

    public Case[][] get9CaseAround(PositionLogique pl){
        Case[][] casesAutour =  new Case[3][3];
        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++){
                casesAutour[x][y] = getCaseOrNull(x+ pl.getCaseRow(),y+ pl.getCaseColumn());
            }
        return casesAutour;
    }

    public ArrayList<Case> getCardinals(PositionLogique pl){
        ArrayList<Case> cases4 = new ArrayList<Case>();
        cases4.add(getCaseOrNull(pl.getCaseRow(),1 + pl.getCaseColumn()));     //haut      -> droite
        cases4.add(getCaseOrNull(pl.getCaseRow(),-1 + pl.getCaseColumn()));    //bas       -> gauche
        cases4.add(getCaseOrNull(1 + pl.getCaseRow(), pl.getCaseColumn()));    //droite    -> haut
        cases4.add(getCaseOrNull(-1 + pl.getCaseRow(), pl.getCaseColumn()));   //gauche    -> bas
        return cases4;
    }

    private Case getCaseOrNull(int x, int y){
        return (x < maxX && y < maxY && x >= 0 && y >= 0) ? tiles[x][y] : null;
    }

    public Case getCaseOrNull(PositionLogique pl){
        for(Case[] caseRow : tiles)
            for(Case c : caseRow)
                if(c.getPositionLog().getCaseRow() == pl.getCaseRow() && c.getPositionLog().getCaseColumn() == pl.getCaseColumn())
                    return c;

        return null;
    }
}
