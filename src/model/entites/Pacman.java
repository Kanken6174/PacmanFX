/**
 * @Author Yorick Geoffre
 * @brief contient l'implémentation de l'entité pacman
 */

package model.entites;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Le pacman est contrôlé par le joueur
 */
public class Pacman extends Entite{

    /** pacAngle définit la rotation actuelle effectuée par le pacman.
     * Il est utilisé à la fois pour dessiner le pacman dans la bonne direction
     * et animer sa bouche. Ecrit par le contrôleur (par l'entrée clavier)
     */
    private DoubleProperty pacAngle = new SimpleDoubleProperty();
        public double getPacAngle(){return pacAngle.get();}
        public void setPacAngle(int value){pacAngle.set(value);}
        public DoubleProperty pacAngleProperty(){return pacAngle;}
}
