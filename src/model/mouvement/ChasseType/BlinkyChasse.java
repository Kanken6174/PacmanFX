package model.mouvement.ChasseType;

import model.mouvement.ChasseComportement;
import model.entites.PacmanObject;
import model.mouvement.Positions.PositionLogique;

public class BlinkyChasse implements ChasseComportement{

    @Override
    public void chasse(PacmanObject cible) {
        PositionLogique posCible = cible.getPositionLogique();
        //DeplaceurFantome.chasserPac(nom, cible, nom.dircetionYeux);
    }
}
