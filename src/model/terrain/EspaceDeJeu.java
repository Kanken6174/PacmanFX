package model.terrain;

import model.entites.Entite;
import model.entites.Fantome;
import model.entites.PacmanObject;
import model.mouvement.Positions.PositionLogique;
import model.partie.CompteurScore;
import model.partie.CompteurVie;
import model.terrain.loaders.collisionLoader;
import model.terrain.loaders.entityLoader;

import java.util.ArrayList;


public class EspaceDeJeu {
    private int maxX = 15;  //lignes
    private int maxY = 28;  //colonnes
    private String levelName = "";
    private Case[][] tiles = new Case[maxX][maxY];
    private CompteurScore cs = new CompteurScore();
    private CompteurVie cv = new CompteurVie();

    public void LoadStage(String StageName){
        levelName = StageName;
        tiles = new Case[maxX][maxY];
        tiles = collisionLoader.loadCollisions(StageName, maxX, maxY);
        tiles = entityLoader.loadEntities(StageName, tiles,maxX, maxY);
    }

    public CompteurScore getCs(){
        return cs;
    }

    public CompteurVie getCv(){
        return cv;
    }

    public void reset(){
        cs = new CompteurScore();
        cv = new CompteurVie();
        LoadStage(levelName);
    }

    public Case[][] getStage(){
        return this.tiles;
    }

    public PacmanObject getPacman(){
        for(int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                Case processed = tiles[x][y];
                if(processed == null){
                    //System.out.println("null cell at:"+x+" "+y);

                }else if(processed.containsPacMan()){
                    System.out.println("pacman at: "+x+" "+y);
                    PacmanObject pac = (PacmanObject)processed.getEntite(processed.getPacmanIndex());
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
                casesAutour[x][y] = getCaseOrNull(x+ pl.getCaseX(),y+ pl.getCaseY());
            }
        return casesAutour;
    }

    public ArrayList<Case> getCardinals(PositionLogique pl){
        ArrayList<Case> cases4 = new ArrayList<Case>();
        cases4.add(getCaseOrNull(pl.getCaseX(),1 + pl.getCaseY()));     //haut      -> droite
        cases4.add(getCaseOrNull(pl.getCaseX(),-1 + pl.getCaseY()));    //bas       -> gauche
        cases4.add(getCaseOrNull(1 + pl.getCaseX(), pl.getCaseY()));    //droite    -> haut
        cases4.add(getCaseOrNull(-1 + pl.getCaseX(), pl.getCaseY()));   //gauche    -> bas
        return cases4;
    }

    private Case getCaseOrNull(int x, int y){
        return (x < maxX && y < maxY && x >= 0 && y >= 0) ? tiles[x][y] : null;
    }
}
