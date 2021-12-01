package Launcher;

import Controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;

public class Launcher extends Application{
    private boolean visible = true;

    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("test");
        stage.setWidth(800);
        stage.setHeight(700);
        stage.setScene(scene);
        Controller controller = loader.getController();
        stage.show();
    }

}