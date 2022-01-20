/**@author Yorick Geoffre
 * @brief Ce fichier contient la source d'un enum*/

package model.enums;

/**
 * énumère le comportement qu'un fantôme adopte:
 * - SCATTER, le fantôme part vers un coin de la carte donné
 * - CHASE, le fantôme suit le pacman
 * - FREIGHTENED, le fantome est bleu et s'enfuit
 * - EATEN, le fantome n'a plus que ses yeux et retourne au point de départ
 */
public enum FantomeState {
    SCATTER, CHASE, FREIGHTENED, EATEN;
}