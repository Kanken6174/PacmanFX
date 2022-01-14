package model.mouvement.ScatterType;

import model.entites.Fantome;
import model.mouvement.Deplaceurs.Deplaceur;
import model.mouvement.ScatterComportement;
import model.mouvement.Deplaceurs.DeplaceurFantome;
import model.mouvement.Positions.PositionLogique;

public class ScatterTopRight implements ScatterComportement {

    @Override
    public void scatter(PositionLogique scatterHome, Fantome cible) {
        while (cible.getPositionLogique() == scatterHome)
            Deplaceur.deplacerEntiteVersCase(scatterHome);
    }
}
