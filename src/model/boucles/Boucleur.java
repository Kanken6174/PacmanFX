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
        Thread t = new Thread(this);
        t.run();
    }

    public void stop() throws InterruptedException {
        running = false;
    }

    public void subscribe(Abonne a){
        abonnes.add(a);
    }

    @Override
    public void run() {
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
