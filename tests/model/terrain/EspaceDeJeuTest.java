package model.terrain;

import model.entites.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        System.out.print("test");
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
                        toAdd = "█";
                    } else if(cases[i][j].isGhostHouseDoor()){
                        toAdd = "_";
                    }else {
                        toAdd = " ";
                    }

                    if(cases[i][j].hasStaticEntities()){
                        Entite e = cases[i][j].getStaticEntite();
                        if(e instanceof Gomme)
                            toAdd = ".";
                        if(e instanceof SuperGomme)
                            toAdd = "°";
                        if(e instanceof Fruits)
                            toAdd = "$";
                    }

                    if(cases[i][j].hasEntities()){
                        Entite e = cases[i][j].getEntite(0);
                        if(e instanceof Fantome)
                            //toAdd = "\uD83D\uDC7B";
                            toAdd = "A";
                        if(e instanceof PacmanObject)
                            toAdd = "C";
                    }
                    toWrite += toAdd;
                }
            }
            System.out.println(toWrite);
        }
    }

}