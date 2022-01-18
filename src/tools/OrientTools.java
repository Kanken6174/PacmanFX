package tools;

import model.enums.Orients;

public class OrientTools {
    public static Orients invertOrient(Orients direction){
        switch (direction){
            case GAUCHE:
                return Orients.DROITE;
            case HAUT:
                return Orients.BAS;
            case BAS:
                return Orients.HAUT;
            default:
                return Orients.GAUCHE;
        }
    }
}
