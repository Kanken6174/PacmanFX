package model.mouvement.ScatterType;

import model.entites.Fantome;
import model.mouvement.Positions.PositionLogique;
import model.mouvement.ScatterComportement;

public class ScatterTopLeft implements ScatterComportement {

    @Override
    public void scatter(PositionLogique scatterHome, Fantome cible) {
        /*while (cible.getPositionLogique() == scatterHome)
            Deplaceur.deplacerEntiteVersCase(scatterHome);*/
    }
}
