package model.Observers;

import java.util.ArrayList;

public class Observable {
    private final ArrayList<Runnable> abonnes = new ArrayList<>();

    public void notifyAbonnes(){
        for (Runnable r: abonnes){
            r.run();
        }
    }

    public void subscribe(Runnable r){
        abonnes.add(r);
    }
    
    public void unsubscribe(Runnable r){
        abonnes.remove(r);
    }

    public int getAbonnesCount(){
        return abonnes.size();
    }
}
