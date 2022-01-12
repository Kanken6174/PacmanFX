package model.mouvement.ScatterType;

import model.entites.Fantome;
import model.mouvement.ScatterComportement;
import model.mouvement.Positions.PositionLogique;
import model.mouvement.Deplaceurs.DeplaceurFantome;

public class ScatterBottomLeft implements ScatterComportement {

    @Override
    public void scatter(PositionLogique scatterHome, Fantome cible) {
        while (cible.getPositionLogique() == scatterHome)
            DeplaceurFantome.versScatter(scatterHome);
    }
}
