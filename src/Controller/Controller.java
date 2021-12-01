package Controller;

import Views.GameView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;

import javax.sound.midi.ControllerEventListener;
import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller {

    private GameView gameview;
    @FXML private Arc pacman;
    @FXML private Stage stage;
    @FXML private BorderPane myBP;

    public Controller(){
        gameview = new GameView(stage);
        runPacRegularly(stage);
    }

    public void initialize() {
    }

    public void runPacRegularly(Stage stage){
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(5, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t ;
        });

        Runnable pacTask = new Runnable() {
            @Override
            public void run() {
                Platform.runLater(() -> runPacMan());
            }
        };

        exec.scheduleAtFixedRate(pacTask, 1, 10, TimeUnit.MILLISECONDS);

    }

    public void runPacMan(){
        pacman.setStartAngle(pacman.getStartAngle());
        pacman.setCenterX(pacman.getCenterX()+1);
        if(pacman.getCenterX() > 500)
            pacman.setCenterX(0);
        pacman.setCenterY(0f);
        System.out.println(pacman.getLength());
        if(pacman.getLength() >= 345)
            pacman.setLength(270f);
        else
            pacman.setLength(pacman.getLength()+1f);
    }

}