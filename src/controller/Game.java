package controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;
import model.enums.FantomeNom;
import model.graphics.Loaders.ImageLoader;
import model.terrain.EspaceDeJeu;
import model.entites.Fantome;
import model.entites.PacmanObject;
import model.graphics.Loaders.ImageMaster;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game {
    @FXML private Stage stage;
    @FXML private BorderPane myBP;
    @FXML private Arc pacman;
    @FXML private Button test;
    @FXML private ImageView fantome1;
    @FXML private ImageView fantome2;
    @FXML private ImageView fantome3;
    @FXML private ImageView fantome4;

    private boolean closingAnimation = false;

    @FXML public PacmanObject pacmanobject = new PacmanObject();
    private EspaceDeJeu playspace = new EspaceDeJeu();
    private Fantome clyde = new Fantome(FantomeNom.CLYDE);
    private Fantome inky = new Fantome(FantomeNom.INKY);
    private Fantome blinky = new Fantome(FantomeNom.BLINKY);
    private Fantome pinky = new Fantome(FantomeNom.PINKY);
    private Fantome[] fantomes= {clyde, inky, blinky, pinky};

    private ImageMaster im = new ImageMaster(ImageLoader.loadRessources());


    @FXML public EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>(){
        @Override
        public void handle(KeyEvent ke){
            Platform.runLater(()-> pacmanobject.changePacOrient(ke.getCode()));
        }
    };

    public Game(){
    }

    @FXML private void initialize() {
        myBP.setOnKeyPressed(eventHandler);
        BufferedImage bi = ImageLoader.loadRessources();
        Image img = im.getImageAt(553,65);
        fantome1.setImage(img);
        runPacRegularly();
        runGhostRegularly();
    }


    public void runPacRegularly(){
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

    public void runGhostRegularly(){
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(15, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t ;
        });

        Runnable fanTask = new Runnable() {
            @Override
            public void run() {
                Platform.runLater(() -> runGhost());
            }
        };
        exec.scheduleAtFixedRate(fanTask, 1, 400, TimeUnit.MILLISECONDS);
    }

    private void runGhost(){
        for(int i = 0; i < 4; i++) {
            if (fantomes[i].spriteX > 441 + (15 * 8)) {
                fantomes[i].spriteX = 441 + 16;
                fantomes[i].spriteY += 16;
                if (fantomes[i].spriteY > 49 + (16 * 4))
                    fantomes[i].spriteY = 65;
            } else
                fantomes[i].spriteX += 16;
            //System.out.println("Running");
            Image img = im.getImageAt(fantomes[i].spriteX, fantomes[i].spriteY);
            fantome1.setImage(img);
        }
    }

    private void runPacMan(){
        if(pacmanobject.getPacX() > 500 || pacmanobject.getPacX() < -500)
            pacmanobject.setPacX(0);
        if(pacmanobject.getPacY() > 500 || pacmanobject.getPacY()  < -500)
            pacmanobject.setPacY(0);

        switch ((int)pacmanobject.getPacAngle()){
            case 0:
                pacmanobject.setPacY(pacmanobject.getPacY()+1);
            case 90:
                pacmanobject.setPacX(pacmanobject.getPacX()+1);
                break;
            case 180:
                pacmanobject.setPacY(pacmanobject.getPacY()-1);
                break;
            case 270:
                pacmanobject.setPacX(pacmanobject.getPacX()-1);
                break;
            default:
                break;
        }
        if(pacman.getLength() >= 345+pacmanobject.getPacAngle()){
            closingAnimation = true;
        }else if(pacman.getLength() < 270+pacmanobject.getPacAngle()){
            closingAnimation = false;
        }
        if (closingAnimation){
            pacman.setLength(pacman.getLength() - 3f);
            pacman.setStartAngle(pacman.getStartAngle()+1f);
        } else {
            pacman.setLength(pacman.getLength() + 3f);
            pacman.setStartAngle(pacman.getStartAngle()-1f);
        }
        positionPacman();
        myBP.setFocusTraversable(true);
        myBP.requestFocus();
    }

    private void positionPacman(){
        pacman.setCenterX(pacmanobject.getPacX());
        pacman.setCenterY(pacmanobject.getPacY());
    }

    public void buttonChangeColor() {
        test.setBackground(Color.BLACK);
    }
}
