package model.mouvement.ChasseType;

import model.entites.Fantome;
import model.enums.Orients;
import model.mouvement.ChasseComportement;
import model.mouvement.Positions.PositionLogique;
import model.terrain.EspaceDeJeu;

import static java.lang.Math.sqrt;

public class BlinkyChasse implements ChasseComportement {

    @Override
    public void chasse(PositionLogique cible, Fantome source) {
        PositionLogique posSource = source.getPositionLogique();
        EspaceDeJeu EJ;
        int directionX = posSource.getCaseX();
        int directionY = posSource.getCaseY();
        double distanceSourceCible = sqrt(((cible.getCaseX() - posSource.getCaseX()) ^ 2 ) + (cible.getCaseY() - posSource.getCaseY()) ^ 2 ); //Le fantome charche la dircetion la plus courte pour atteindre le fantome
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
