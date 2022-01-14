package model.boucles;

import javafx.application.Platform;

import java.util.ArrayList;

public class Boucleur implements Runnable{
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
            Platform.runLater(this::notifyAbonnes);
            }
        }
    }
