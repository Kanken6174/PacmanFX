package model;

import model.terrain.EspaceDeJeu;

public class Manager {

    public void init(){
        EspaceDeJeu EJ = new EspaceDeJeu();
        EJ.LoadStage("level");
    }
}
