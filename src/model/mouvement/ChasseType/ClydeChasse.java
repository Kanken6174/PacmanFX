package model.mouvement.ChasseType;

import model.collisions.Hitbox;
import model.collisions.HitboxCircle;
import model.entites.Fantome;
import model.entites.PacmanObject;
import model.enums.Orients;
import model.mouvement.ChasseComportement;
import model.mouvement.Positions.PositionLogique;

import static java.lang.Math.sqrt;


public class ClydeChasse implements ChasseComportement {

    @Override
    public void chasse(PositionLogique cible, Fantome source) {
        PositionLogique posSource = source.getPositionLogique();
        double distanceSourceCible = sqrt(((cible.getCaseX() - posSource.getCaseX()) ^ 2 ) + (cible.getCaseY() - posSource.getCaseY()) ^ 2 );
        if (distanceSourceCible <= 8) //Si pacman se trouve dans un rayon de 8 cases de clyde...
            source.scatter(); // Le fantome passe en mode scatter
        else{
            int directionX = posSource.getCaseX();
            int directionY = posSource.getCaseY();
            if (sqrt((((cible.getCaseX() - directionX - 1) ^ 2 ) + (cible.getCaseY() - directionY) ^ 2 )) < distanceSourceCible)
                source.setDircetionYeux(Orients.GAUCHE);
            else
            if (sqrt((((cible.getCaseX() - directionX + 1) ^ 2 ) + (cible.getCaseY() - directionY) ^ 2 )) < distanceSourceCible)
                source.setDircetionYeux(Orients.DROITE);
            else
            if (sqrt((((cible.getCaseX() - directionX) ^ 2 ) + (cible.getCaseY() - directionY - 1) ^ 2 )) < distanceSourceCible)
                source.setDircetionYeux(Orients.HAUT);
            else
                source.setDircetionYeux(Orients.BAS); //On oriente le fantome en direction de pacman

            //DeplaceurFantome.deplacement(); //Le fantome se déplace d'une case à la fois
        }
    }
}
