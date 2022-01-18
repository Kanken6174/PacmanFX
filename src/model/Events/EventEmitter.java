package model.Events;

import model.Events.Events.Event;

import java.util.ArrayList;

public abstract class EventEmitter {
    protected ArrayList<EventListener> listeners = new ArrayList<EventListener>();
    protected Event localEvent; //le type de l'évenement (hérité) définit comment il se comporte

    public void sendEvent(){
        for(EventListener listener : listeners)
            listener.HandleEvent(localEvent);
    }

    public void setLocalEvent(Event event){
        localEvent = event;
    }

    public void addListener(EventListener ev){
        listeners.add(ev);
    }

    public void removeListener(EventListener ev){
        listeners.remove(ev);
    }
}