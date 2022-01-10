package model.boucles;

import java.util.ArrayList;

public class GestionnaireBoucles {
    public ArrayList<Boucleur> boucles = new ArrayList<>();

    public void scheduleAll() {
    }

    public void schedule(Runnable r, int periode){
        for(Boucleur b: boucles){
            if(b.getPeriode() == periode) {
                b.addAbonne(r);
                return;
            }

        }
        Boucleur b = new Boucleur(periode);
        b.addAbonne(r);
        boucles.add(b);
    }

    public void stop(){
        for(Boucleur b : boucles)
            b.stop();
    }

    public void run(){
        for(Boucleur b : boucles)
            b.run();
    }
}
