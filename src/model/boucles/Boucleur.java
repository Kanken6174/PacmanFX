package model.boucles;

public class Boucleur extends Observable{
    private int periode;

    public Boucleur(int periode){
        this.periode = periode;
    }

    public void run() {
        System.out.println("BIM");
        try {
            Thread.sleep(periode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
