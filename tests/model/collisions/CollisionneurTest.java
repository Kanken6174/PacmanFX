package model.collisions;

import model.mouvement.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CollisionneurTest {
    private Collisionneur col = new Collisionneur();

    @Test
    void circleCircleCollision() {
        Position CirclePos = new Position(0, 0);
        Position CirclePos2 = new Position(1, 0);

        HitboxCircle HBC = new HitboxCircle(3);
        HitboxCircle HBC2 = new HitboxCircle(3);

        assertFalse(col.isInCollision(CirclePos, CirclePos2, HBC, HBC2));   //la collisions est désactivée par défaut, c'est donc faux.

        HBC.toggleCollision();
        HBC2.toggleCollision();

        assertTrue(col.isInCollision(CirclePos, CirclePos2, HBC, HBC2));

        CirclePos = new Position(0, -7);
        assertFalse(col.isInCollision(CirclePos, CirclePos2, HBC, HBC2));

        CirclePos = new Position(3, 2);
        assertTrue(col.isInCollision(CirclePos, CirclePos2, HBC, HBC2));
    }
    @Test
    void SquareSquareCollision(){
        Position SquarePos = new Position(0,0);
        Position SquarePos2 = new Position(0,10);

        HitboxSquare HS = new HitboxSquare(10);
        HitboxSquare HS2 = new HitboxSquare(10);

        assertFalse(col.isInCollision(SquarePos, SquarePos2, HS, HS2));

        HS.toggleCollision();
        HS2.toggleCollision();

        /*        rappel: (dans ce cas ce sera le Z de la position traitée)
        pointsCarre[0] = bas    gauche  ->  [1]-----[3]
        pointsCarre[1] = haut   gauche   ->  |       |
        pointsCarre[2] = bas    droit  ->    |       |
        pointsCarre[3] = haut   droit   ->  [0]-----[2]
         */

        assertTrue(col.isInCollision(SquarePos, SquarePos2, HS, HS2));

        SquarePos2 = new Position(0,0); //chevauchés
        assertTrue(col.isInCollision(SquarePos, SquarePos2, HS, HS2));

        SquarePos2 = new Position(5,6);//en diagonale, sans se toucher
        assertFalse(col.isInCollision(SquarePos, SquarePos2, HS, HS2));

        SquarePos2 = new Position(1f,1f);//en diagonale, se touchant
        assertTrue(col.isInCollision(SquarePos, SquarePos2, HS, HS2));
    }
}