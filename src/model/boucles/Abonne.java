/**
 * @author Yorick geoffre
 * @brief Ce fichier contient les sources de l'interface Abonne
 */

package model.boucles;

/**
 * Les classes implémentant cette interfaces peuvent s'abonner à une boucle pour être notifié à temps fixe
 */
public interface Abonne {

    /**
     * Cette méthode devra être @Override par la classe implémentante, elle est appellée à chacke "tick" de la boucle abonnée
     */
    public void doAction();
}
