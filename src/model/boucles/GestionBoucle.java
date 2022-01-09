package model.boucles;

import views.Animateurs.AnimateurPacMan;

import java.util.ArrayList;

public class GestionBoucle{
    public ArrayList<Boucleur> boucles = new ArrayList<>();

    public void scheduleAll() {
    }

    public void scheduleSpecific(){

    }

    public void schedulePacmanAnimation(AnimateurPacMan a, int periode){
        Boucleur b = new Boucleur(periode);
        b.addAbonne(a);
        b.run();
    }

    public void animateMortPacman() {
    }

    public void animateRespawnFantome() {
    }
}
