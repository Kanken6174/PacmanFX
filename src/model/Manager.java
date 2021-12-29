package model;

import model.boucles.GestionBoucle;
import model.terrain.EspaceDeJeu;

public class Manager {

    public void init(){
        GestionBoucle GE = new GestionBoucle();
        EspaceDeJeu EJ = new EspaceDeJeu();

        
        GE.scheduleAll();
    }
}
