package views;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;
import model.boucles.GestionnaireBoucles;
import model.entites.Entite;
import model.entites.Fantome;
import model.entites.PacmanObject;
import views.Animateurs.Animateur;
import views.Animateurs.AnimateurPacMan;

import java.util.ArrayList;

public class gameView {
    @FXML
    private Stage stage;
    @FXML private BorderPane myBP;
    @FXML private Arc pacman;
    @FXML private ImageView blinky;
    @FXML private ImageView pinky;
    @FXML private ImageView inky;
    @FXML private ImageView clyde;
    @FXML private ImageView terrain;

    @FXML private ImageView[] fantomes = {blinky,pinky,inky,clyde};


    @FXML
    public void initialize(){
    }

    public void DrawPlayspace(WritableImage img){
        terrain.setImage(img);
        terrain.setRotate(90);
        terrain.setScaleX(terrain.getScaleX()*3);
        terrain.setScaleY(terrain.getScaleY()*3);
    }

    public void DrawEntities(GestionnaireBoucles gb, ArrayList<Entite> entites){
        AnimateurPacMan a = new AnimateurPacMan(pacman);

        for(Entite e : entites){
            if(e instanceof PacmanObject) {
                bindPacman((PacmanObject) e);
            }else if(e instanceof Fantome){
                Animateur af = new Animateur(((Fantome) e).getSpriteAnimable());
                bindFantome((Fantome) e);
                gb.schedule(af,200);
            }
        }
        gb.schedule(a,8);
    }

    public void bindPacman(PacmanObject pac){
        pacman.rotateProperty().bind(pac.pacAngleProperty());
    }

    public void bindFantome(Fantome f){
        switch (f.getFantomeNom()){
            case BLINKY:
                blinky.imageProperty().bind(f.getSpriteAnimable().CurrentFrameProperty());
                break;
            case INKY:
                inky.imageProperty().bind(f.getSpriteAnimable().CurrentFrameProperty());
                break;
            case CLYDE:
                clyde.imageProperty().bind(f.getSpriteAnimable().CurrentFrameProperty());
                break;
            case PINKY:
                pinky.imageProperty().bind(f.getSpriteAnimable().CurrentFrameProperty());
                break;
            default:
                break;
        }
    }

}
