package model.mouvement.ChasseType;

import model.entites.Fantome;
import model.entites.PacmanObject;
import model.mouvement.ChasseComportement;
import model.mouvement.Positions.PositionLogique;


public class ClydeChasse implements ChasseComportement {

    @Override
    public void chasse(PacmanObject cible, Fantome nom) {
        String nomGhost = "CLYDE";
        if (!nom.identifier.equals(nomGhost)) {
            return;
        }
        else {
            PositionLogique posCible = cible.getPositionLogique();
            //if (Collisionneur.CheckCollision2Circles(cible.getPositionGraphique(), nom.getPositionGraphique()))
            //DeplaceurFantome.chasserPac(nom, cible, nom.dircetionYeux);
        }
    }
}
