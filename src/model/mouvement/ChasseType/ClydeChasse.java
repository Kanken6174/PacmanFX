package model.mouvement.ChasseType;

import model.collisions.Collisionneur;
import model.mouvement.ChasseComportement;
import model.entites.Fantome;
import model.entites.PacmanObject;
import model.mouvement.Deplaceurs.DeplaceurFantome;
import model.mouvement.Positions.PositionLogique;


public class ClydeChasse implements ChasseComportement {

    @Override
    public void chasse(PacmanObject cible) {
        PositionLogique posCible = cible.getPositionLogique();
        //if (Collisionneur.CheckCollision2Circles(cible.getPositionGraphique(), nom.getPositionGraphique()))
    }
}
