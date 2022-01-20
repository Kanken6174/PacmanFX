/**@Author Yorick Geoffre
 * @Brief contient un évènement*/

package model.Events.Events;

import model.entites.Mangeable;

/**
 * Le ScoreObjectEatenEvent est lancé par le CollisionResolver du déplaceur à chaque fois que le pacman entre sur une case
 * ayant des objets Mangeables. Cet évènement contient le score de l'objet mangé et fait réagir le compteur de score,
 * qui s'incrémente du nombre de points donné.
 */
public class ScoreObjectEatenEvent extends Event{
    private int score = 0;
    public ScoreObjectEatenEvent(Mangeable mangeable){
        score = mangeable.getScore();
    }

    public int getScore(){
        return score;
    }
}