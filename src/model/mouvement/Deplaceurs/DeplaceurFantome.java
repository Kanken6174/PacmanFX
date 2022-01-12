package model.mouvement.Deplaceurs;

import model.entites.Entite;
import model.enums.Orients;
import model.mouvement.Positions.PositionLogique;

public class DeplaceurFantome extends Deplaceur {

    public static void versScatter(PositionLogique posScatter) {}

    public static void chasserPac(Entite source, Entite cible, Orients direction) {
        deplacerEntite(source, cible, direction);
    }
}
