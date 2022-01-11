package model.entites;

import model.enums.FantomeNom;
import model.mouvement.Positions.PositionLogique;

public interface ChasseComportement {
    public void goTo(PositionLogique pos);
    public void chasse(PacmanObject cible, FantomeNom nom);
}
