/**@author Yorick Geoffre
 * @brief Ce fichier contient les sources d'un émetteur d'évènements abstrait*/

package model.Events;

import model.Events.Events.Event;

import java.util.ArrayList;

/**
 * Similaire au fonctionnement des boucles de jeu, un EventEmitter va notifier ses abonnés (ou EventListeners ici)
 * en leur envoyant l'Event qu'il stocke en local. le type de cet évènement va changer la réaction des EventListeners
 * concernés par le type d'évènement envoyé.
 */
public abstract class EventEmitter {
    protected ArrayList<EventListener> listeners = new ArrayList<EventListener>();
    protected Event localEvent; //le type de l'évenement (hérité) définit comment il se comporte

    /**
     * Envoie l'évènement local aux abonnés (EventListeners)
     */
    public void sendEvent(){
        for(EventListener listener : listeners)
            listener.HandleEvent(localEvent);
    }

    /**
     * Permet de changer l'évènement local
     * @param event le nouvel évènement local
     */
    public void setLocalEvent(Event event){
        localEvent = event;
    }

    /**
     * Permet d'ajouter un abonné (EventListener)
     * @param ev l'EventListener à ajouter
     */
    public void addListener(EventListener ev){
        listeners.add(ev);
    }

    /**
     * Permet de retirer un EventListener
     * @param ev l'EventListener à retirer
     */
    public void removeListener(EventListener ev){
        listeners.remove(ev);
    }
}