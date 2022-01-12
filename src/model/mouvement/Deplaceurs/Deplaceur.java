package model.mouvement.Deplaceurs;

import model.entites.Entite;
import model.enums.Orients;
import model.mouvement.Navigateurs.Navigateur;

public abstract class Deplaceur implements Runnable{
    protected Navigateur navigateur;

    public static void deplacerEntite(Entite source, Entite cible, Orients direction){
    }

    public void run(){

    }
}