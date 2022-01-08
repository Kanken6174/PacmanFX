package views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;

public class gameView {
    @FXML
    private Stage stage;
    @FXML private BorderPane myBP;
    @FXML private Arc pacman;
    @FXML private Button test;
    @FXML private ImageView fantome1;
    @FXML private ImageView fantome2;
    @FXML private ImageView fantome3;
    @FXML private ImageView fantome4;
    @FXML private ImageView terrain;

    public gameView(){
    }


    @FXML
    public void initialize(){

    }

    public void DrawPlaspaceBackground(WritableImage img){
        terrain.setImage(img);
        terrain.setRotate(90);
        terrain.setScaleX(terrain.getScaleX()*3);
        terrain.setScaleY(terrain.getScaleY()*3);
    }

}
