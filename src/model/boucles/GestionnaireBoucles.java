package model.boucles;

import model.Events.EventListener;
import model.Events.Events.EndGameEvent;
import model.Events.Events.Event;

import java.util.ArrayList;

public class GestionnaireBoucles implements EventListener {
    private ArrayList<Boucleur> boucles = new ArrayList<Boucleur>();

    public GestionnaireBoucles(){

    }

    public void scheduleLoop(Abonne a, int periode){
        for(Boucleur b : boucles){
            if(b.getPeriode() == periode){
                b.subscribe(a);
                return;
            }
        }
        //pas trouvé
        BoucleurStd boucleur = new BoucleurStd(periode);
        boucleur.subscribe(a);
        boucles.add(boucleur);
    }

    public void Start(){
        for(Boucleur b : boucles)
            b.start();
    }

    public void Stop(){
        try {
            for (Boucleur b : boucles)
                b.stop();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void HandleEvent(Event e) {
        if(e instanceof EndGameEvent){
            Stop();
        }
    }
}
