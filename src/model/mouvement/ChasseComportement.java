package model.mouvement;

import model.entites.Fantome;
import model.entites.PacmanObject;
import model.enums.FantomeNom;
import model.mouvement.Positions.PositionLogique;

public interface ChasseComportement {
    public void chasse(PacmanObject cible, Fantome nom);
}
