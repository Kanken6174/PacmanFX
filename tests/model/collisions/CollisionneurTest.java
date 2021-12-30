package model.collisions;

import model.mouvement.PositionGraphique;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CollisionneurTest {
    private Collisionneur col = new Collisionneur();

    @Test
    void circleCircleCollision() {
        PositionGraphique CirclePos = new PositionGraphique(0, 0);
        PositionGraphique CirclePos2 = new PositionGraphique(1, 0);

        HitboxCircle HBC = new HitboxCircle(3);
        HitboxCircle HBC2 = new HitboxCircle(3);

        assertFalse(col.isInCollision(CirclePos, CirclePos2, HBC, HBC2));   //la collisions est désactivée par défaut, c'est donc faux.

        HBC.toggleCollision();
        HBC2.toggleCollision();

        assertTrue(col.isInCollision(CirclePos, CirclePos2, HBC, HBC2));

        CirclePos = new PositionGraphique(0, -7);
        assertFalse(col.isInCollision(CirclePos, CirclePos2, HBC, HBC2));

        CirclePos = new PositionGraphique(3, 2);
        assertTrue(col.isInCollision(CirclePos, CirclePos2, HBC, HBC2));
    }
    @Test
    void SquareSquareCollision(){
        PositionGraphique SquarePos = new PositionGraphique(0,0);
        PositionGraphique SquarePos2 = new PositionGraphique(0,10);

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

        SquarePos2 = new PositionGraphique(0,0); //chevauchés
        assertTrue(col.isInCollision(SquarePos, SquarePos2, HS, HS2));

        SquarePos2 = new PositionGraphique(10,11);//en diagonale, sans se toucher
        assertFalse(col.isInCollision(SquarePos, SquarePos2, HS, HS2));

        SquarePos2 = new PositionGraphique(10,10);//en diagonale, se touchant
        assertTrue(col.isInCollision(SquarePos, SquarePos2, HS, HS2));
    }
    @Test
    void TestSquareCircleCollision(){
        PositionGraphique SquarePos = new PositionGraphique(0,0);
        PositionGraphique CirclePos = new PositionGraphique(0, 0);

        HitboxSquare HS = new HitboxSquare(10);
        HitboxCircle HBC = new HitboxCircle(3);

        assertFalse(col.isInCollision(SquarePos, CirclePos, HS, HBC));

        HS.toggleCollision();
        HBC.toggleCollision();

        assertTrue(col.isInCollision(SquarePos, CirclePos, HS, HBC));

        CirclePos = new PositionGraphique(0,0); //chevauchés
        assertTrue(col.isInCollision(SquarePos, CirclePos, HS, HBC));

        CirclePos = new PositionGraphique(17,11);//en diagonale, sans se toucher
        assertFalse(col.isInCollision(SquarePos, CirclePos, HS, HBC));

        CirclePos = new PositionGraphique(5,5);//en diagonale, se touchant
        assertTrue(col.isInCollision(SquarePos, CirclePos, HS, HBC));
    }
}