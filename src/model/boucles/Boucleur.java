package model.boucles;

import model.Observers.Observable;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Boucleur extends Observable {
    private int periode = 100;
    private Boolean running = false;
    private ArrayList<Runnable> abonnes = new ArrayList<Runnable>();;
    private ScheduledExecutorService exec = null;

    public Boucleur(int periodeMillis){
        this.periode = periodeMillis;
        this.abonnes = new ArrayList<Runnable>();
        exec = Executors.newScheduledThreadPool(this.abonnes.size(),r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t ;
        });
    }

    public Boucleur(int periodeMillis, ArrayList<Runnable> abonnes){
        this.abonnes = abonnes;
        this.periode = periodeMillis;
        exec = Executors.newScheduledThreadPool(this.abonnes.size(),r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t ;
        });
    }

    public void setPeriod(int periodeMillis){
        this.periode = periodeMillis;
        stop();
        run();
    }

    public void run() {
        running = true;
        for(Runnable r : abonnes)
            exec.scheduleAtFixedRate(r, 0, this.periode, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        try {
            running = false;
            exec.awaitTermination(800, TimeUnit.MILLISECONDS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public Boolean isRunning() {
        return this.running;
    }

    public void addAbonne(Runnable a){
        abonnes.add(a);
    }
}
