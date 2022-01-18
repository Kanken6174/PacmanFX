package model.Events.Events;

import model.entites.Fantome;

public class GhostEatenEvent extends Event{
    Fantome eaten = null;
    public GhostEatenEvent(Fantome f){
        eaten = f;
    }

    public Fantome getGhost(){
        return eaten;
    }
}
