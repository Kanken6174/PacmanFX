package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import launcher.Launcher;

public class GameOverView {

    @FXML private Button goMenuBtn;
    @FXML private Label scoreLabel;

    private Stage stage;

    private int score = 0;

    @FXML public void initialize(){

    }

    public void receiveStage(Stage s){
        this.stage = s;
    }

    public void passScore(String score){
        scoreLabel.textProperty().set(score);
    }

    public void goMenu(ActionEvent actionEvent) {
        Launcher l = new Launcher();
        try {
            l.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
