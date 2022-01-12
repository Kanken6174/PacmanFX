package model.mouvement;

import model.entites.Fantome;
import model.mouvement.Positions.PositionLogique;

public interface ScatterComportement {

    public void scatter(PositionLogique scatterHome, Fantome cible);
}
