/**
 * @author Yorick geoffre
 * @brief Ce fichier contient les sources du gestionnaire des boucles
 */

package model.boucles;

import model.Events.EventListener;
import model.Events.Events.EndGameEvent;
import model.Events.Events.Event;
import java.util.ArrayList;

/**
 * Le GestionnaireBoucles va gérer les différentes boucles de jeu et décider si un abonné
 * doit être ajouté à une boucle existante ou si il doit en créer une nouvelle
 */
public class GestionnaireBoucles implements EventListener {
    private ArrayList<Boucleur> boucles = new ArrayList<Boucleur>();

    /**
     * Va ajouter un abonné à une boucle existante, ou instancier une nouvelle boucle
     * @param a l'abonné à ajouter
     * @param periode la période souhaitée entre chaque notification
     */
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

    /**
     * Lance toutes les boucles gérées
     */
    public void Start(){
        for(Boucleur b : boucles)
            b.start();
    }

    /**
     * Stoppe toutes les boucles gérées
     */
    public void Stop(){
            for (Boucleur b : boucles)
                b.stop();
    }

    @Override
    /**
     * Le gestionnaire de boucles est lui-même un EventListener, et il attend l'évènement de fin
     * de jeu pour arrêter toutes ses boucles, pour que tous les threads soient bien à l'arrêt
     * en fin de jeu.
     */
    public void HandleEvent(Event e) {
        if(e instanceof EndGameEvent){
            Stop();
        }
    }
}
