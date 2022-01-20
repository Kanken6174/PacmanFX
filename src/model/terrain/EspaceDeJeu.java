/**@Author Yorick Geoffre
 * @brief contient les sources de l'espace de jeu (terrain)*/

package model.terrain;

import model.entites.Entite;
import model.entites.Fantome;
import model.entites.Gomme;
import model.entites.Pacman;
import model.mouvement.Positions.PositionLogique;
import model.terrain.loaders.CollisionLoader;
import model.terrain.loaders.entityLoader;

import java.util.ArrayList;

/**
 * L'espace de jeu possède les cases dans un double tableau, il sert globalement de manager pour le modèle et possède la
 * grande partie des objets qui y sont liés (spécifiquement les entités et les cases), il appelle également les loaders
 * pour charger ses données de collisions et d'entités pour chaque niveau.
 */
public class EspaceDeJeu {
    private int maxX = 15;  //lignes du tableau terrain
    private int maxY = 28;  //colonnes du tableau terrain

    private int PelletsRemaining = 999;

    private String levelName = "";
    private Case[][] terrain = new Case[maxX][maxY];

    public void LoadStage(String StageName){
        levelName = StageName;
        terrain = new Case[maxX][maxY];
        terrain = CollisionLoader.loadCollisions(StageName, maxX, maxY);
        terrain = entityLoader.loadEntities(StageName, terrain,maxX, maxY);
        countPellets();
    }

    public void LoadStage(String StageName, int columns, int rows){
        maxX = rows;
        maxY = columns;
        levelName = StageName;
        terrain = new Case[maxX][maxY];
        terrain = CollisionLoader.loadCollisions(StageName, maxX, maxY);
        terrain = entityLoader.loadEntities(StageName, terrain,maxX, maxY);
        countPellets();
    }

    public int getPelletsRemaining(){
        return PelletsRemaining;
    }

    public void countPellets(){
        int pellets = 0;
        for(int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                Case traitee =  getCaseOrNull(x,y);
                if(traitee != null && traitee.hasStaticEntities() && traitee.getStaticEntite() instanceof Gomme)
                    pellets++;
            }
        }
        PelletsRemaining = pellets;
    }

    public void decrementPellets(){
        PelletsRemaining--;
    }

    public int getMaxX(){
        return maxX;
    }

    public int getMaxY(){
        return maxY;
    }

    public void reset(){
        LoadStage(levelName);
    }

    public Case[][] getStage(){
        return this.terrain;
    }

    public Pacman getPacman(){
        for(int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                Case processed = terrain[x][y];
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
                Case processed = terrain[x][y];
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
                Case processed = terrain[x][y];
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
                Case processed = terrain[x][y];
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
        return (x < maxX && y < maxY && x >= 0 && y >= 0) ? terrain[x][y] : null;
    }

    public Case getCaseOrNull(PositionLogique pl){
        for(Case[] caseRow : terrain)
            for(Case c : caseRow)
                if(c.getPositionLog().getCaseRow() == pl.getCaseRow() && c.getPositionLog().getCaseColumn() == pl.getCaseColumn())
                    return c;

        return null;
    }
}
