package model.collisions;

public class HitboxSquare extends Hitbox {
    public HitboxSquare(float sideLength){
        CollisionDistance = sideLength;
        SurfaceType = 1;
    }
}
