package model.partie;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CompteurScore {
    public int score;
    private StringProperty scoreValue = new SimpleStringProperty();

    public void incrementer(int ptsAjoutes){
        this.score = this.score + ptsAjoutes;
    }

    public String getScoreValue() {
        return scoreValue.get();
    }

    public void setScoreValue(String scoreValue) {
        this.scoreValue.set(scoreValue);
    }

    public StringProperty scoreValueProperty() {
        return scoreValue;
    }
}
