package model.partie;

public class CompteurVie {
    int vies = 3;

    public void reset(){
        vies = 3;
    }

    public void decrease(){
        vies--;
    }
}