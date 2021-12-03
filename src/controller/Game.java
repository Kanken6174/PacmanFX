package controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game {
    @FXML private Stage stage;
    @FXML private BorderPane myBP;
    @FXML private Arc pacman;

    private boolean closingAnimation = false;

    private int pacAngle = 0;

    @FXML public EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>(){
        @Override
        public void handle(KeyEvent ke){
            Platform.runLater(()-> changePacOrient(ke.getCode()));
        }
    };

    public Game(){
    }

    @FXML private void initialize() {
        myBP.setOnKeyPressed(eventHandler);
        runPacRegularly(stage);
    }


    public void runPacRegularly(Stage stage){
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(15, r -> {
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
        exec.scheduleAtFixedRate(pacTask, 1, 7, TimeUnit.MILLISECONDS);
    }

    private void runPacMan(){
        if(pacman.getCenterX() > 500 || pacman.getCenterX() < -500)
            pacman.setCenterX(0);
        if(pacman.getCenterY() > 500 || pacman.getCenterY() < -500)
            pacman.setCenterY(0f);

        switch ((int)pacAngle){
            case 0:
                pacman.setCenterY(pacman.getCenterY()+1);
                break;
            case 90:
                pacman.setCenterX(pacman.getCenterX()+1);
                break;
            case 180:
                pacman.setCenterY(pacman.getCenterY()-1);
                break;
            case 270:
                pacman.setCenterX(pacman.getCenterX()-1);
                break;
            default:
                break;
        }
        if(pacman.getLength() >= 345+pacAngle){
            closingAnimation = true;
        }else if(pacman.getLength() < 270+pacAngle){
            closingAnimation = false;
        }
        if (closingAnimation){
            pacman.setLength(pacman.getLength() - 3f);
            pacman.setStartAngle(pacman.getStartAngle()+1f);
        } else {
            pacman.setLength(pacman.getLength() + 3f);
            pacman.setStartAngle(pacman.getStartAngle()-1f);
        }
        myBP.setFocusTraversable(true);
        myBP.requestFocus();
    }

    private void changePacOrient(KeyCode kc){
        switch (kc){
            case UP:
                pacAngle = 0;
                break;
            case DOWN:
                pacAngle = 180;
                break;
            case LEFT:
                pacAngle = 270;
                break;
            case RIGHT:
                pacAngle = 90;
                break;
            default:
                break;
        }
        pacman.setStartAngle(pacAngle);
    }
}
