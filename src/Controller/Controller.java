package Controller;

import Views.GameView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller {

    private GameView gameview;

    public void initialize(Stage stage) {
    gameview = new GameView(stage);
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
                Platform.runLater(() -> gameview.runPacMan());
            }
        };

        exec.scheduleAtFixedRate(pacTask, 1, 100, TimeUnit.MILLISECONDS);

    }
}