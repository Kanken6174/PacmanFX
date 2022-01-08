package views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;
import model.entites.Fantome;

public class gameView {
    @FXML
    private Stage stage;
    @FXML private BorderPane myBP;
    @FXML private Arc pacman;
    @FXML private Button test;
    @FXML private ImageView blinky;
    @FXML private ImageView pinky;
    @FXML private ImageView inky;
    @FXML private ImageView clyde;
    @FXML private ImageView terrain;

    @FXML private ImageView[] fantomes = {blinky,pinky,inky,clyde};

    @FXML
    public void initialize(){

    }

    public void DrawPlayspaceBackground(WritableImage img){
        terrain.setImage(img);
        terrain.setRotate(90);
        terrain.setScaleX(terrain.getScaleX()*3);
        terrain.setScaleY(terrain.getScaleY()*3);
    }

    public void bindFantome(Fantome f){
        ImageView toBind = fantomes[f.getFantomeNom().ordinal()];
        toBind.xProperty().bindBidirectional(f.getPositionGraphique().xProperty());
        toBind.yProperty().bindBidirectional(f.getPositionGraphique().yProperty());
    }

}
