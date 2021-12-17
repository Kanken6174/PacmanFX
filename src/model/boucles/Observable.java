package model.boucles;

import model.Abonne;

public class Observable {
    Abonne[] abonnes = new Abonne[300];

    public void notifyAbonnes(){
    }

    public void subscribe(Abonne a){
        abonnes[(abonnes.length) + 1] = a;
    }
    
    public void unsubscribe(Abonne a){
    }
}
