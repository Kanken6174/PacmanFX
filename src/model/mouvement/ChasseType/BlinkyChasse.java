package model.mouvement.ChasseType;

import model.entites.Fantome;
import model.entites.PacmanObject;
import model.mouvement.ChasseComportement;
import model.mouvement.Positions.PositionLogique;

public class BlinkyChasse implements ChasseComportement{

    @Override
    public void chasse(PacmanObject cible, Fantome nom) {
        String nomGhost = "BLINKY";
        if (!nom.identifier.equals(nomGhost)) {
            return;
        }
        else {
            PositionLogique posCible = cible.getPositionLogique();
            //DeplaceurFantome.chasserPac(nom, cible, nom.dircetionYeux);
        }
    }
}
