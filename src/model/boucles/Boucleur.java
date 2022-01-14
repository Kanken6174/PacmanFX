package model.boucles;

import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;

public class Boucleur implements Runnable{
    private ScheduledExecutorService exec = null;
    private ArrayList<Abonne> abonnes = new ArrayList<Abonne>();
    private int periode = 100;
    private boolean running;

    public Boucleur(int periode){
        this.periode = periode;
    }

    public void start(){
        running = true;
        Thread t = new Thread(this::run);
        t.start();
    }

    public void stop() throws InterruptedException {
        running = false;
    }

    public void subscribe(Abonne a){
        abonnes.add(a);
    }

    public int getPeriode(){
        return this.periode;
    }

    @Override
    public void run() {     //boucle de jeu
        while(running) {
            try {
                Thread.sleep(periode);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (Abonne a : abonnes) {
                a.doAction();
            }
        }
    }
}
