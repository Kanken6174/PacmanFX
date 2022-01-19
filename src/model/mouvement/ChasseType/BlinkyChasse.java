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
        int directionX = posSource.getCaseRow();
        int directionY = posSource.getCaseColumn();
        double distanceSourceCible = sqrt(((cible.getCaseRow() - posSource.getCaseRow()) ^ 2 ) + (cible.getCaseColumn() - posSource.getCaseColumn()) ^ 2 ); //Le fantome charche la dircetion la plus courte pour atteindre le fantome
        if (sqrt((((cible.getCaseRow() - directionX - 1) ^ 2 ) + (cible.getCaseColumn() - directionY) ^ 2 )) < distanceSourceCible)
            source.setDircetionYeux(Orients.GAUCHE);
        else
            if (sqrt((((cible.getCaseRow() - directionX + 1) ^ 2 ) + (cible.getCaseColumn() - directionY) ^ 2 )) < distanceSourceCible)
                source.setDircetionYeux(Orients.DROITE);
            else
                if (sqrt((((cible.getCaseRow() - directionX) ^ 2 ) + (cible.getCaseColumn() - directionY - 1) ^ 2 )) < distanceSourceCible)
                    source.setDircetionYeux(Orients.HAUT);
                else
                    source.setDircetionYeux(Orients.BAS); //On oriente le fantome en direction de pacman

        //DeplaceurFantome.deplacement(); //Le fantome se déplace d'une case à la fois
    }
}
