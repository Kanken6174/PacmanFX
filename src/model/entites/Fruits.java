package model.entites;

public class Fruits extends Entite{
    public String type;
    Type t;
    int points;
    int spriteX = 471; //Début des sprites des fruits
    int spriteY = 33; //Ligne des sprites des fruits

    private int getScore(Type t){
        return t.getPoints();
    }
}
