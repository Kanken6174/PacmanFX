package views;

import javafx.beans.property.ObjectProperty;
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
import model.terrain.EspaceDeJeu;
import views.Animateurs.Animateur;
import views.Animateurs.AnimateurPacMan;
import views.Sprites.SpriteManager;
import views.ViewEntities.EntiteVue;
import views.ViewEntities.EntiteVueAnimable;

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

    private SpriteManager sm = null;

    @FXML
    public void initialize(){
    }

    public void loadRessources(EspaceDeJeu ej){
        sm = new SpriteManager(ej); //charge aussi toutes les données de tous les sprites
        DrawPlayspace(sm.getTerrainBackground());
        bindPacman(ej.getPacman());
    }

    public void DrawPlayspace(WritableImage img){
        terrain.setImage(img);
        terrain.setRotate(90);
        terrain.setScaleX(terrain.getScaleX()*3);
        terrain.setScaleY(terrain.getScaleY()*3);
    }

    public void DrawEntities(GestionnaireBoucles gb, ArrayList<Entite> entites){
        AnimateurPacMan a = new AnimateurPacMan(pacman);
        gb.schedule(a,8);
        for(Entite e : entites){
            if(e instanceof PacmanObject) {
                bindPacman((PacmanObject) e);
            }else if(e instanceof Fantome){
                Animateur af = new Animateur(((Fantome) e).getSpriteAnimable());
                gb.schedule(af,200);
            }
        }
    }

    public void DrawEntities(GestionnaireBoucles gb){
        AnimateurPacMan a = new AnimateurPacMan(pacman);
        gb.schedule(a,8);
        ArrayList<EntiteVue> ev = sm.getEntiteVues();
        for(EntiteVue e : ev){
            if(e.getSource() instanceof PacmanObject) {
                bindPacman((PacmanObject) e.getSource());
            }else if(e.getSource() instanceof Fantome){
                Animateur af = new Animateur(((Fantome) e.getSource()).getSpriteAnimable());
                bindFantome((EntiteVueAnimable) e);
                gb.schedule(af,200);
            }
        }
    }

    public void bindPacman(PacmanObject pac){
        pacman.rotateProperty().bind(pac.pacAngleProperty());
    }

    public void bindFantome(EntiteVueAnimable s){
        Fantome f = (Fantome) s.getSource();
        ObjectProperty<WritableImage> pr = s.getSpriteProperty();
        switch (f.getFantomeNom()){
            case BLINKY:
                blinky.imageProperty().bind(pr);
                break;
            case INKY:
                inky.imageProperty().bind(pr);
                break;
            case CLYDE:
                clyde.imageProperty().bind(pr);
                break;
            case PINKY:
                pinky.imageProperty().bind(pr);
                break;
            default:
                break;
        }
    }

}
