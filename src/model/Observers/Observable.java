package model.Observers;

import java.util.ArrayList;

public class Observable {
    private final ArrayList<Abonne> abonnes = new ArrayList<>();

    public void notifyAbonnes(){
        for (Abonne abonne: abonnes){
            abonne.miseAJour();
        }
    }

    public void subscribe(Abonne a){
        abonnes.add(a);
    }
    
    public void unsubscribe(Abonne a){
        abonnes.remove(a);
    }

    public int getAbonnesCount(){
        return abonnes.size();
    }
}
