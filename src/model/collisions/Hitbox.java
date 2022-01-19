/**
 * @author Yorick geoffre
 * @brief Ce fichier contient les sources de l'ancien système de Hitbox (inutilisé)
 */

package model.collisions;

/**
 * Le système de collisions qui allait de paire avec le Collisionneur
 * @deprecated Hitbox
 */
public abstract class Hitbox {
    protected float CollisionDistance = 0; //Donne la "valeur" de la largeur de la boîte de collision, c'est le rayon sur un cercle, et le coté sur un carre
    protected int SurfaceType = -1;   //type de surface invalide, 0 pour cercle, 1 pour carré (utilisé par le collisionneur)
    public boolean CollisionEnabled = false;

    public float getCollisionDistance() {
        return CollisionDistance;
    }

    public void setCollisionDistance(float newDistance){
        if(newDistance < 0)
            newDistance = 0;
        CollisionDistance = newDistance;
    }

    public boolean isValidesSurfaceType(){
        return (SurfaceType > -1 && SurfaceType < 2);   //0 pour carré, 1 pour cercle
    }

    public int getSurfaceType(){
        return  SurfaceType;
    }

    public float getTrueCollisionDistance(){
        return 0;
    }

    public void toggleCollision(){
        this.CollisionEnabled = !this.CollisionEnabled;
    }
}
