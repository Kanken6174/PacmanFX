/**
 * @author Yorick geoffre
 * @brief Ce fichier contient les sources d'une extension de l'ancien système de collisions (version cercle)
 */

package model.collisions;

/**
 * Une implémentation de Hitbox qui prend la forme d'un cercle
 * @deprecated
 */
public class HitboxCircle extends Hitbox {
    public HitboxCircle(float radius) {
        CollisionDistance = radius;
        SurfaceType = 0;
    }
}