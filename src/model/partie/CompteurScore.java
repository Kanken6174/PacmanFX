/**@author Yorick Geoffre
 * @brief Ce fichier contient les sources du compteur de score*/

package model.partie;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Events.EventListener;
import model.Events.Events.Event;
import model.Events.Events.ScoreObjectEatenEvent;

/**
 * Le compteur de score répond à un évènement du type ScoreObjectEatenEvent et
 * garde le compte du score actuel.
 */
public class CompteurScore implements EventListener {
    private IntegerProperty score = new SimpleIntegerProperty();
        public int getScore() {return score.get();}
        public void setScoreValue(int value){this.score.set(value);}
        public IntegerProperty Scoreproperty(){return score;}

    private StringProperty playerName = new SimpleStringProperty();
        public String getPlayerName() {return playerName.get();}
        public void setPlayerName(String scoreValue) {this.playerName.set(scoreValue);}
        public StringProperty playerNameProperty() {return playerName;}

    public CompteurScore(String playerName){
            this.playerName.set(playerName);
            this.score.set(0);
    }

    public Runnable incrementer(int ptsAjoutes){
        return new Runnable() {
            @Override
            public void run() {
                score.set(score.get() + ptsAjoutes);
            }
        };
    }

    @Override
    public void HandleEvent(Event e) {
        if(e instanceof ScoreObjectEatenEvent){
            int score = ((ScoreObjectEatenEvent) e).getScore();
            Platform.runLater(incrementer(score));
        }
    }

}