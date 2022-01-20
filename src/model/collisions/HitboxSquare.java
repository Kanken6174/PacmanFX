/**
 * @author Yorick geoffre
 * @brief Ce fichier contient les sources d'une extension de l'ancien système de collisions (version carré)
 */

package model.collisions;
/**
 * Une implémentation de Hitbox qui prend la forme d'un carré
 * @deprecated
 */
public class HitboxSquare extends Hitbox {
    public HitboxSquare(float sideLength){
        CollisionDistance = sideLength;
        SurfaceType = 1;
    }
}
