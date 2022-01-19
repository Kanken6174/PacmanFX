package model.mouvement;

import model.entites.Fantome;
import model.enums.Orients;
import model.mouvement.Positions.PositionLogique;

import static java.lang.Math.sqrt;

public class ScatterComportement {

    public void scatter(PositionLogique scatterHome, Fantome cible) {
        PositionLogique posCible = cible.getPositionLogique();
        double distanceScatterGhost = sqrt(((posCible.getCaseRow() - scatterHome.getCaseRow()) ^ 2 ) + (posCible.getCaseColumn() - scatterHome.getCaseColumn()) ^ 2 );

        while (distanceScatterGhost != 0) { //On vérifie la distance la plus courte vers le point de scatter
            int directionX = posCible.getCaseRow();
            int directionY = posCible.getCaseColumn();
            if (sqrt((((scatterHome.getCaseRow() - directionX - 1) ^ 2) + (scatterHome.getCaseColumn() - directionY) ^ 2)) < distanceScatterGhost)
                cible.setDircetionYeux(Orients.GAUCHE);
            else if (sqrt((((scatterHome.getCaseRow() - directionX + 1) ^ 2) + (scatterHome.getCaseColumn() - directionY) ^ 2)) < distanceScatterGhost)
                cible.setDircetionYeux(Orients.DROITE);
            else if (sqrt((((scatterHome.getCaseRow() - directionX) ^ 2) + (scatterHome.getCaseColumn() - directionY - 1) ^ 2)) < distanceScatterGhost)
                cible.setDircetionYeux(Orients.HAUT);
            else
                cible.setDircetionYeux(Orients.BAS); //On change l'orientation du fantome en direction du chemin le plus court;

            //DeplaceurFantome.deplacement(); //Le fantome se déplace une case à la fois

            posCible = cible.getPositionLogique(); //On récupère de nouveau la position du fantome
        }

        cible.chasse(); // Un fois le point de scatter atteint, le fantome passe en mode chasse
    }
}
