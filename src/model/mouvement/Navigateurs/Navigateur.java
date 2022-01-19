/**@author Yorick Geoffre
 * @Brief contient les sources de la classe abstraite Navigateur*/

package model.mouvement.Navigateurs;

import model.entites.Entite;
import model.enums.Orients;
import model.terrain.Case;
import model.terrain.EspaceDeJeu;

import java.util.ArrayList;

/**Un navigateur représente l'IA derrière une entité, elle aide le déplaceur à choisir une case en proposant une direction
 * souhaitée.*/
public abstract class Navigateur {
    /**
     * Permet de donner une direction à prendre au Deplaceur
     * @param e l'entité qui est concernée
     * @param ej l'espace de jeu actuel
     * @return une direction à prendre (de puis l'enum orients)
     */
    public Orients donneDirectionAPrendre(Entite e, EspaceDeJeu ej){
        int direction = 0;
        ArrayList<Case> casesCardinales= ej.getCardinals(e.getPositionLogique());

        return Orients.values()[0];
    }
}