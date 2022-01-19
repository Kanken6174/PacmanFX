package model.mouvement;

import model.entites.Fantome;
import model.mouvement.Positions.PositionLogique;

public interface ChasseComportement {
    public void chasse(PositionLogique cible, Fantome source);
}
