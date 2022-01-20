package model.Events.Events;

import model.entites.Mangeable;

public class ScoreObjectEatenEvent extends Event{
    private int score = 0;
    public ScoreObjectEatenEvent(Mangeable mangeable){
        score = mangeable.getScore();
    }

    public int getScore(){
        return score;
    }
}