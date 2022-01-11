package model.entites.ChasseType;

import model.entites.ChasseComportement;
import model.entites.PacmanObject;
import model.enums.FantomeNom;
import model.mouvement.Positions.PositionLogique;

public class PinkyChasse implements ChasseComportement {

    @Override
    public void goTo(PositionLogique pos){}

    @Override
    public void chasse(PacmanObject cible, FantomeNom nom) {
        String nomGhost = "PINKY";
        if (!nom.equals(nomGhost)) {
            return;
        }
        else {
            PositionLogique posCible = cible.getPositionLogique();
            goTo(posCible);
        }
    }
}
