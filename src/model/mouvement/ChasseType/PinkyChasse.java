package model.mouvement.ChasseType;

import model.mouvement.ChasseComportement;
import model.entites.Fantome;
import model.entites.PacmanObject;
import model.mouvement.Positions.PositionLogique;

public class PinkyChasse implements ChasseComportement {

    @Override
    public void chasse(PacmanObject cible) {
        String nomGhost = "PINKY";
        PositionLogique posCible = cible.getPositionLogique();
    }
}