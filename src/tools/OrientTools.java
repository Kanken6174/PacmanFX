/**@author Yorick Geoffre
 * @brief contient les sources de l'outil d'invertion d'orientation
 */

package tools;

import model.enums.Orients;

public class OrientTools {
    /**
     * @param direction la direction à inverser
     * @return la direction inverse (enum)
     */
    public static Orients invertOrient(Orients direction){
        switch (direction){
            case GAUCHE:
                return Orients.DROITE;
            case HAUT:
                return Orients.BAS;
            case BAS:
                return Orients.HAUT;
            default:
                return Orients.GAUCHE;
        }
    }
}
