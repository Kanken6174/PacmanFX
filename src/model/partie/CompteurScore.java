/**@author Yorick Geoffre
 * @brief Ce fichier contient les sources du compteur de score*/

package model.partie;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Events.EventListener;
import model.Events.Events.EndGameEvent;
import model.Events.Events.Event;
import model.Events.Events.GhostEatenEvent;
import model.Events.Events.ScoreObjectEatenEvent;
import tools.files.ScoreSaver;

/**
 * Le compteur de score répond à un évènement du type ScoreObjectEatenEvent et
 * garde le compte du score actuel.
 * L'évènement EndGameEvent va le forcer à enregistrer ce score.
 */
public class CompteurScore implements EventListener {
    /**Le score actuel*/
    private IntegerProperty score = new SimpleIntegerProperty();
        public int getScore() {return score.get();}
        public void setScoreValue(int value){this.score.set(value);}
        public IntegerProperty Scoreproperty(){return score;}
    /**Le nom du joueur qui a effectué ce score*/
    private StringProperty playerName = new SimpleStringProperty();
        public String getPlayerName() {return playerName.get();}
        public void setPlayerName(String scoreValue) {this.playerName.set(scoreValue);}
        public StringProperty playerNameProperty() {return playerName;}

    private StringProperty levelName = new SimpleStringProperty();
        public String getLevelName() {return levelName.get();}
        public void setLevelName(String scoreValue) {this.levelName.set(scoreValue);}
        public StringProperty LevelNameProperty() {return levelName;}

    public CompteurScore(String playerName, String levelName){
            this.playerName.set(playerName);
            this.levelName.set(levelName);
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
        if(e instanceof EndGameEvent){
            ScoreSaver.saveScore(levelName.getValue(), playerName.getValue(), getScore());
        }
        if(e instanceof GhostEatenEvent){
            Platform.runLater(incrementer(200));
        }
    }

}