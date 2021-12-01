package Views;

import javafx.fxml.FXML;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;

public class GameView {
    @FXML private Arc pacman;
    @FXML private Stage stage;
    public GameView(Stage stage){
       this.stage = stage;
    }

}
