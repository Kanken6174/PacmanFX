package model.collisions;

public class HitboxCircle extends Hitbox {
    public HitboxCircle(float radius) {
        CollisionDistance = radius;
        SurfaceType = 0;
    }
}