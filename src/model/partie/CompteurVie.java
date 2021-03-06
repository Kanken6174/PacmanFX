/**@author Yorick Geoffre
 * @brief Ce fichier contient les sources du compteur de vies*/

package model.partie;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.Events.ConcreteEmitter;
import model.Events.EventListener;
import model.Events.Events.EndGameEvent;
import model.Events.Events.Event;
import model.Events.Events.PacmanDeathEvent;

/**
 * Le compteur de vie va garder en mémoire le nombre de vies restantes au joueur.
 * Il répond à un PacmanDeathEvent et décrémente la vie lorsque pacman meurt.
 */
public class CompteurVie implements EventListener {
    private ConcreteEmitter em;

    public CompteurVie(ConcreteEmitter em){
        this.em = em;
        reset();
    }
    /**Le nombre de vies restantes (0-3)*/
    private IntegerProperty vies = new SimpleIntegerProperty();
        public int getVies() {return vies.get();}
        public void setVies(int value){this.vies.set(value);}
        public IntegerProperty Viesproperty(){return vies;}

    public void reset(){
        vies.set(3);
    }

    public void decrease(){
        vies.set(vies.getValue()-1);
        if(vies.getValue() <= 0){
            em.setLocalEvent(new EndGameEvent());
            em.sendEvent();
        }
    }

    @Override
    public void HandleEvent(Event e) {
        if(e instanceof PacmanDeathEvent){
            decrease();
        }
    }
}