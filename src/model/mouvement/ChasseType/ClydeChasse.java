package model.mouvement.ChasseType;

import model.entites.Fantome;
import model.enums.Orients;
import model.mouvement.ChasseComportement;
import model.mouvement.Positions.PositionLogique;

import static java.lang.Math.sqrt;


public class ClydeChasse implements ChasseComportement {

    @Override
    public void chasse(PositionLogique cible, Fantome source) {
        PositionLogique posSource = source.getPositionLogique();
        double distanceSourceCible = sqrt(((cible.getCaseRow() - posSource.getCaseRow()) ^ 2 ) + (cible.getCaseColumn() - posSource.getCaseColumn()) ^ 2 );
        if (distanceSourceCible <= 8) //Si pacman se trouve dans un rayon de 8 cases de clyde...
            source.scatter(); // Le fantome passe en mode scatter
        else{
            int directionX = posSource.getCaseRow();
            int directionY = posSource.getCaseColumn();
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
}
