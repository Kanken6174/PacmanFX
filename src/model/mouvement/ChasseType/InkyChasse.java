package model.mouvement.ChasseType;

import model.mouvement.ChasseComportement;
import model.entites.Fantome;
import model.entites.PacmanObject;
import model.mouvement.Positions.PositionLogique;

public class InkyChasse implements ChasseComportement {

    @Override
    public void chasse(PacmanObject cible){
        String nomGhost = "INKY";
        PositionLogique posCible = cible.getPositionLogique();
    }
}
