/**@Author Yorick Geoffre
 * @brief contient les sources de la Case*/

package model.terrain;

import model.entites.Entite;
import model.entites.Fantome;
import model.entites.Pacman;
import model.mouvement.Positions.PositionLogique;

import java.util.ArrayList;

/**
 * Une case est un objet utilisé par l'EspaceDeJeu pour sotcker des informations relatives au terrain et
 * aux entité présentes.
 * <br/>
 * Une case peut avoir une seule entité statique à la fois (une gomme, un fruit... ect) et plusieurs entités
 * "dynamiques" (fantomes, pacman).
 * <br/>
 * La case contient aussi une SpriteAnchor pour que les vues sachent comment la représenter, ainsi qu'une position logique
 * pour faciliter les transferts d'entités (c'est la position de la case dans le double tableau du terrain - EspaceDeJeu)
 */
public class Case {
    /**Si la case est un obstacle ou non*/
    private boolean estObstacle = true;
    /**Si la case est une porte à fantômes ou non*/
    private boolean isGhostHouseDoor = false;
    /**Si la case contient actuellement le pacman ou non*/
    private boolean containsPacMan = false;
    /**La position de la case dans le double tabelau du terrain*/
    private final PositionLogique pl;
    /**La liste des entités actuellement dans cette case*/
    private ArrayList<Entite> Entites = new ArrayList<>();
    /**L'entité statique actuellement contenue dans cette case*/
    private Entite EntiteStatique = null;

    /**Le constructeur de la case
     *
     * @param X sa position X dans le tableau du terrain
     * @param Y sa position Y dans le tableau du terrain
     */
    public Case(int X, int Y) {
        pl = new PositionLogique(X,Y);
    }

    public PositionLogique getPositionLog(){
        return pl;
    }

    public void setEstObstacle(Boolean b){
        estObstacle = b;
    }
    public void setGhostHouseDoor(Boolean b){ isGhostHouseDoor = b;}

    public boolean isObstacle(){
        return estObstacle;
    }

    public boolean isGhostHouseDoor(){return isGhostHouseDoor;}

    /**
     * Cette méthode permet à une case de recevoir une entité (habituellement passée depuis une autre case)
     * @param e l'entité à recevoir
     */
    public void ReceiveEntity(Entite e){
        if(Entites.contains(e) || e == null)
            return;
        Entites.add(e);
        if(e instanceof Pacman)
            containsPacMan = true;
    }

    /**
     * Cette méthode permet à une case de recevoir une entite statique
     * @param e l'entité à recevoir
     */
    public void ReceiveStaticEntity(Entite e){
        if(EntiteStatique == null)
            EntiteStatique = e;
    }

    /**
     * @deprecated utiliser passEntity(Entite e) à la place
     * @param index l'idex de l'entité à passer
     * @return l'entité passée
     */
    public Entite passEntity(int index){    //"passe" l'entité à cet index à une autre case par exemple
        Entite e = Entites.get(index);
        Entites.remove(e);
        if(e instanceof Pacman)
            containsPacMan = false;
        return e;
    }

    /**
     * Permet à une case de "passer" une entité spécifique à une autre case en prenant sa référence, l'enlevant de sa liste,
     * et en la retournant.
     * @param e l'entité à passer
     * @return l'entité à passer
     */
    public Entite passEntity(Entite e){
        if(Entites.contains(e)){
            Entites.remove(e);
            containsPacMan = false;
            return e;
        }else{
            return null;
        }
    }
    /**
     * Permet de récupérer une entité depuis son index
     * @param i l'index de l'entite
     * @return l'entite
     */
    public Entite getEntite(int i){
        if(i > Entites.size())
            return null;
        return Entites.get(i);
    }

    public Entite getStaticEntite(){
        return EntiteStatique;
    }

    public boolean hasEntities(){
        return (Entites.size() > 0);
    }

    public boolean hasGhosts(){
        boolean toReturn = false;
        for(Entite e : Entites){
            if(e instanceof Fantome){
                toReturn = true;
                break;
            }

        }
        return toReturn;
    }

    public ArrayList<Entite> getAllEntite(){
        return this.Entites;
    }

    public ArrayList<Integer> getGhostIndexes(){
        ArrayList<Integer> indexs = new ArrayList<>();
        for(Entite e : Entites){
            if(e instanceof Fantome){
                indexs.add(Entites.indexOf(e));
            }
        }
        return indexs;
    }

    public ArrayList<Fantome> getGhosts(){
        ArrayList<Fantome> ghosts = new ArrayList<Fantome>();
        for(Entite e : Entites)
            if(e instanceof Fantome)
                ghosts.add((Fantome) e);

        return ghosts;
    }

    public int getPacmanIndex() {
        if (containsPacMan) {
            int i = 0;
            for (Entite e : Entites) {
                if (e instanceof Pacman) {
                    return i;
                }
                i++;
            }
        }
        return -1;  //cas d'erreur
    }

    public boolean hasStaticEntities(){
        return (EntiteStatique != null);
    }

    public boolean containsPacMan(){
        return containsPacMan;
    }

    public void setEntiteStatique(Entite es){
        this.EntiteStatique = es;
    }
}