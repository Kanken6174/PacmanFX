/**
 * @author Yorick geoffre
 * @brief Ce fichier contient les sources du boucleur abstrait
 */

package model.boucles;

import java.util.ArrayList;

/**
 * Le boucleur va s'exécuter sur un thread à part et va notifier ses abonnés à intervalles régulières (selon sa période)
 */
public abstract class Boucleur implements Runnable{
    /** les abonnés qui seront notifiés */
    protected ArrayList<Abonne> abonnes = new ArrayList<Abonne>();
    /** la période entre chaque notification */
    protected int periode = 100;
    /** si le boucleur devrait fonctionner ou non */
    protected boolean running;

    public Boucleur(int periode){
        this.periode = periode;
    }

    /**
     * Lance la boucle
     */
    public void start(){
        running = true;
        Thread t = new Thread(this::run);
        t.start();
    }

    /**
     * Arrête la boucle
     */
    public void stop() {
        running = false;
    }

    /**
     * Ajoute un abonné
     * @param a l'abonné à ajouter à la boucle
     */
    public void subscribe(Abonne a){
        abonnes.add(a);
    }

    /**
     * @return la période de la boucle
     */
    public int getPeriode(){
        return this.periode;
    }

    /**
     * Va notifier chaque abonné
     */
    public void notifyAbonnes() {
        for (Abonne a : abonnes) {
            a.doAction();
        }
    }

    @Override
    public void run() {     //boucle de jeu
        while(running) {
            try {
                Thread.sleep(periode);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notifyAbonnes();
            }
        }
    }
