package model.mouvement.ChasseType;

import model.mouvement.ChasseComportement;
import model.entites.Fantome;
import model.entites.PacmanObject;
import model.mouvement.Positions.PositionLogique;

public class PinkyChasse implements ChasseComportement {

    @Override
    public void chasse(PacmanObject cible, Fantome nom) {
        String nomGhost = "PINKY";
        if (!nom.identifier.equals(nomGhost)) {
            return;
        }
        else {
            PositionLogique posCible = cible.getPositionLogique();
        }
    }
}
