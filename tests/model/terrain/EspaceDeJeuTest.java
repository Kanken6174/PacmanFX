package model.terrain;

import model.entites.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EspaceDeJeuTest {
    @Test
    public void TestLoad(){
        EspaceDeJeu EJ = new EspaceDeJeu();
        assertNotNull(EJ);
        EJ.LoadStage("level");
        assertNotNull(EJ);
        Case[][] cases = EJ.getStage();
        assertNotNull(cases);
        drawGameStageToConsole(cases);
        //System.out.print("\033[H\033[2J");
        //System.out.flush();
    }

    private void drawGameStageToConsole(Case[][] cases){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        for(int i = 0;i < cases.length; i++){
            String toWrite = "";
            for(int j = 0; j < cases[i].length; j++){
                String toAdd = "§";
                if(cases[i][j] != null) {
                    if (cases[i][j].isObstacle()) {
                        toAdd = "#";
                    } else {
                        toAdd = " ";
                    }

                    if(cases[i][j].hasStaticEntities()){
                        Entite e = cases[i][j].getStaticEntite();
                        if(e instanceof Gomme)
                            toAdd = "◎";
                        if(e instanceof SuperGomme)
                            toAdd = "\uD83C\uDF59";
                        if(e instanceof Fruits)
                            toAdd = "\uD83C\uDF4E";
                    }

                    if(cases[i][j].hasEntities()){
                        Entite e = cases[i][j].getEntite(0);
                        if(e instanceof Fantome)
                            toAdd = "\uD83D\uDC7B";
                        if(e instanceof PacmanObject)
                            toAdd = "\uD83D\uDFE1";
                    }
                    toAdd += "\t";
                    toWrite += toAdd;
                }
            }
            System.out.println(toWrite);
        }
    }

}