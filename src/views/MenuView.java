package views;

import controller.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuView{
    @FXML private Parent root;
    @FXML private Stage stage;

    @FXML
    public void initialize(){
    }

    @FXML
    public void MenuClicked(){
    }

    public void receiveStage(Stage stage){
        this.stage = stage;
    }

    public void PlayClicked(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameView view = loader.getController();
        GameController gm = new GameController(view);

        root.setOnKeyPressed(gm);   //ça devrait nous éviter d'avoir à demander le focus à chaque fois

        root.requestFocus();
        Scene scene = new Scene(root);
        stage.setTitle("pacmanFX");
        stage.setWidth(800);
        stage.setHeight(700);
        stage.setScene(scene);

        stage.show();
        root.requestFocus();
    }
}
