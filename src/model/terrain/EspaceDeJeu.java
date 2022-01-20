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

    private int PelletsRemaining = 999; //le nombre de gommes restantes sur le terrain (0 = victoire)

    private String levelName = "";  //le nom du niveau à charger
    private Case[][] terrain = new Case[maxX][maxY];//le terrain, un double tableau de Cases

    /**@deprecated Ce constructeur ne supporte pas les LevelFile en entrée
     *
     * @param StageName le nom du niveau à charger
     */
    public void LoadStage(String StageName){
        levelName = StageName;
        terrain = new Case[maxX][maxY];
        terrain = CollisionLoader.loadCollisions(StageName, maxX, maxY);
        terrain = entityLoader.loadEntities(StageName, terrain,maxX, maxY);
        countPellets();
    }

    /**
     * le constructeur de l'espaceDeJeu
     * @param StageName
     * @param columns
     * @param rows
     */
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

    /**
     * Va "compter" le nombre de gommes restantes sur le terrain, devrait être appellé uniquement par le constructeur, une fois.
     */
    private void countPellets(){
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

    /**
     * Va rechercher le pacman sur le terrain
     * @return le pacman
     */
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

    /**
     * Une version de débogage pour trouver la position du pacman sur le terrain
     * @return la position logique du pacman, trouvée en parcourant le tableau
     */
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

    /**
     * Va chercher et retourner tous les fantômes présents sur le terrain
     * @return les fantômes du terrain
     */
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

    /**
     * Va chercher et retourner toutes les entités présentes sur le terrain
     * @return les entités non-statiques du terrain
     */
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

    /**@deprecated ne devrait pas être requis
     * <br/><br/>Permet d'obtenir les 9 cases autour d'une case donnée
     * @param pl la position logique de la case voulue
     * @return les 9 cases autour de cette case
     */
    public Case[][] get9CaseAround(PositionLogique pl){
        Case[][] casesAutour =  new Case[3][3];
        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++){
                casesAutour[x][y] = getCaseOrNull(x+ pl.getCaseRow(),y+ pl.getCaseColumn());
            }
        return casesAutour;
    }

    /**
     * Renvoie les 4 cases autour d'une case (directions cardinales)
     * @param pl  la case cible (position logique)
     * @return les 4 cases
     */
    public ArrayList<Case> getCardinals(PositionLogique pl){
        ArrayList<Case> cases4 = new ArrayList<Case>();
        cases4.add(getCaseOrNull(pl.getCaseRow(),1 + pl.getCaseColumn()));     //haut      -> droite
        cases4.add(getCaseOrNull(pl.getCaseRow(),-1 + pl.getCaseColumn()));    //bas       -> gauche
        cases4.add(getCaseOrNull(1 + pl.getCaseRow(), pl.getCaseColumn()));    //droite    -> haut
        cases4.add(getCaseOrNull(-1 + pl.getCaseRow(), pl.getCaseColumn()));   //gauche    -> bas
        return cases4;
    }

    /**
     * Renvoie la case à cet emplacement
     * @param x position X dans le tableau
     * @param y position Y dans le tableau
     * @return la Case à cet emplacement, ou null si elle n'existe pas
     */
    private Case getCaseOrNull(int x, int y){
        return (x < maxX && y < maxY && x >= 0 && y >= 0) ? terrain[x][y] : null;
    }

    /**
     * Renvoie la case dont la position logique est exactement la même que celle donnée
     * @param pl la position logique cible
     * @return la case correspondante à cette positionLogique, ou null si elle n'existe pas
     */
    public Case getCaseOrNull(PositionLogique pl){
        for(Case[] caseRow : terrain)
            for(Case c : caseRow)
                if(c.getPositionLog().getCaseRow() == pl.getCaseRow() && c.getPositionLog().getCaseColumn() == pl.getCaseColumn())
                    return c;

        return null;
    }
}
