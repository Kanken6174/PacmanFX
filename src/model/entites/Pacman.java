/**
 * @Author Yorick Geoffre
 * @brief contient l'implémentation de l'entité pacman
 */

package model.entites;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
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

    private final BooleanProperty SuperPacman = new SimpleBooleanProperty();
        public boolean isSuper() {return SuperPacman.get();}
        public void becomeSuper(){if(SuperPacman.get()){return;}; this.SuperPacman.set(true);
                                    Thread superTime = new Thread(()->{
                                        try {
                                            Thread.sleep(10000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        Platform.runLater(()->{SuperPacman.set(false);});});
                                    superTime.start();}
        public BooleanProperty SuperPacmanProperty(){return SuperPacman;}
}
