/**@Author Yorick Geoffre
 * @Brief contient un évènement*/
package model.Events.Events;

import model.entites.Fantome;
/**
 * Definit un évènement de fantôme mangé par pacman @unimplemented
 */
public class GhostEatenEvent extends Event{
    Fantome eaten = null;
    public GhostEatenEvent(Fantome f){
        eaten = f;
    }

    public Fantome getGhost(){
        return eaten;
    }
}
