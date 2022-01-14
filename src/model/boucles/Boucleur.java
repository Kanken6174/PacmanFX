package model.boucles;

import java.util.ArrayList;

public abstract class Boucleur implements Runnable{
    protected ArrayList<Abonne> abonnes = new ArrayList<Abonne>();
    protected int periode = 100;
    protected boolean running;

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
