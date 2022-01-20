/*package model.mouvement.ScatterType;

import model.entites.Fantome;
import model.enums.Orients;
import model.mouvement.Deplaceurs.DeplaceurFantome;
import model.mouvement.Positions.PositionLogique;
import model.mouvement.ScatterComportement;

import static java.lang.Math.sqrt;

public class ScatterBottomLeft implements ScatterComportement {

    @Override
    public void scatter(PositionLogique scatterHome, Fantome cible) {
        PositionLogique posCible = cible.getPositionLogique();
        double distanceScatterGhost = sqrt(((posCible.getCaseX() - scatterHome.getCaseX()) ^ 2 ) + (posCible.getCaseY() - scatterHome.getCaseY()) ^ 2 );

        while (distanceScatterGhost != 0) {
            int directionX = posCible.getCaseX();
            int directionY = posCible.getCaseY();
            if (sqrt((((scatterHome.getCaseX() - directionX - 1) ^ 2) + (scatterHome.getCaseY() - directionY) ^ 2)) < distanceScatterGhost)
                cible.setDircetionYeux(Orients.GAUCHE);
            else if (sqrt((((scatterHome.getCaseX() - directionX + 1) ^ 2) + (scatterHome.getCaseY() - directionY) ^ 2)) < distanceScatterGhost)
                cible.setDircetionYeux(Orients.DROITE);
            else if (sqrt((((scatterHome.getCaseX() - directionX) ^ 2) + (scatterHome.getCaseY() - directionY - 1) ^ 2)) < distanceScatterGhost)
                cible.setDircetionYeux(Orients.HAUT);
            else
                cible.setDircetionYeux(Orients.BAS);

            //DeplaceurFantome.deplacement()

            posCible = cible.getPositionLogique();
        }

        cible.chasse();
    }
}
*/