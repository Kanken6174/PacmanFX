package tools.files;

public class ArchivedScore {
    private String playerName;
    private int Score;

    public ArchivedScore(String playerName, String score){
        Score = Integer.valueOf(score);
        this.playerName = playerName;
    }

    public int getScore() {
        return Score;
    }

    public String getPlayerName(){
        return playerName;
    }
}
