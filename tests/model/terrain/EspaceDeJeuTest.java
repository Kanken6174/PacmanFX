package model.terrain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EspaceDeJeuTest {
    @Test
    public void TestLoad(){
        EspaceDeJeu EJ = new EspaceDeJeu();
        assertNotNull(EJ);
        EJ.LoadStage("level");
    }

}