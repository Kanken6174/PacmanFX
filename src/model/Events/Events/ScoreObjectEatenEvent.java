package model.Events.Events;

import model.entites.Type;

public class ScoreObjectEatenEvent extends Event{
    private int score = 0;
    public ScoreObjectEatenEvent(Type objet){
        score = objet.getPoints();
    }

    public int getScore(){
        return score;
    }
}