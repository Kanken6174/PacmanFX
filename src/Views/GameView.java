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
    @FXML
    public void runPacMan(){

        pacman.setStartAngle(pacman.getStartAngle());
        pacman.setCenterX(0f);
        pacman.setCenterY(0f);
        System.out.println(pacman.getLength());
        if(pacman.getLength() >= 345)
            pacman.setLength(270f);
        else
            pacman.setLength(pacman.getLength()+1f);
    }
}
