package model.entites;

import model.enums.FantomeNom;
import model.enums.FantomeState;
import model.enums.Orients;
import model.mouvement.ChasseComportement;
import model.mouvement.ChasseType.BlinkyChasse;
import model.mouvement.ChasseType.ClydeChasse;
import model.mouvement.ChasseType.InkyChasse;
import model.mouvement.ChasseType.PinkyChasse;
import model.mouvement.Positions.PositionLogique;
import model.mouvement.ScatterComportement;

public class Fantome extends Entite{
    public int spriteX = 441;   //définit la position du sprite sur la palette (X)
    public int spriteY = 49;    //début du sprite des fantomes  (Y)

    private PositionLogique scatterHome;
    private PositionLogique target;

    public Orients dircetionYeux; //La direction des yeux indique la direction du fantome

    public FantomeNom identifier;     //définit spriteY et de quel fantome il s'agit
    private FantomeState fs = FantomeState.SCATTER;

    private PacmanObject p;

    private ChasseComportement c;

    private ScatterComportement s;

    //private Orients wantedDirection = Orients.HAUT;
    /**
     *
     * @param fn l'id du fantome, de 0 à 3 (enum)
     */

    public Fantome(FantomeNom fn, ScatterComportement scatType, PacmanObject target){
        identifier = fn;
        spriteY += 16*identifier.ordinal();    //0-3
        p = target;
        s = scatType;

        c = setChasseType(fn);
    }

    public Fantome(FantomeNom fn){
        identifier = fn;
        spriteY += 16*identifier.ordinal();    //0-3
    }

    public FantomeNom getFantomeNom(){
        return identifier;
    }


    public ChasseComportement setChasseType(FantomeNom nomGhost) {
        ChasseComportement typeChasse;

        switch (nomGhost) {
            case BLINKY:
                typeChasse = new BlinkyChasse();
                break;
            case INKY:
                typeChasse = new InkyChasse();
                break;
            case PINKY:
                typeChasse = new PinkyChasse();
                break;
            case CLYDE:
                typeChasse = new ClydeChasse();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + nomGhost);
        }
        return typeChasse;
    }

    public String getC() {
        String typeChasse = null;

        switch (identifier) {
            case BLINKY:
                typeChasse = "CHASSE";
                break;
            case INKY:
                typeChasse = "CAPRICIEUX";
                break;
            case PINKY:
                typeChasse = "AMBUSCADE";
                break;
            case CLYDE:
                typeChasse = "ALEATOIRE";
                break;
        }

        return typeChasse;
    }

    public void chasse() { c.chasse(p); }

    public void scatter() { s.scatter(scatterHome, this); }
}
