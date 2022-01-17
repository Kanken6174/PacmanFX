package views;

import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.boucles.GestionnaireBoucles;
import model.entites.Fantome;
import model.entites.PacmanObject;
import model.enums.FantomeNom;
import model.mouvement.Deplaceurs.DeplaceurPacMan;
import model.mouvement.Positions.PositionLogique;
import model.terrain.Case;
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
    private EspaceDeJeu ej = null;
    @FXML
    public void initialize(){
    }

    public void loadRessources(EspaceDeJeu ej){
        sm = new SpriteManager(ej); //charge aussi toutes les données de tous les sprites
        this.ej = ej;
        DrawPlayspace(sm.getTerrainBackground());
        DrawCollisionMapDebug();
    }

    public void DrawCollisionMapDebug(){
        myBP.getChildren().removeIf(n -> (n instanceof Rectangle) || (n instanceof Circle));
        PositionLogique pol = ej.getPacman().getPositionLogique();
        System.out.println("Pacman posl at:"+pol.getCaseX()+" "+pol.getCaseY());
        int scaleFactor = 12;

        Case cases[][] = ej.getStage();
        for(int x = 0; x < cases[0].length; x++)
            for (int y = 0; y < cases.length ; y++) {
                Rectangle newRect = new Rectangle();
                newRect.setX((x*scaleFactor)+100);
                newRect.setY((y*scaleFactor)+350);
                newRect.setHeight(8*scaleFactor);
                newRect.setWidth(8*scaleFactor);
                Case[] CaseRow = cases[y];
                if(CaseRow[x] != null && CaseRow[x].isObstacle())
                    newRect.setFill(Color.RED);
                else
                    newRect.setFill(Color.BLACK);
                if(CaseRow[x] != null && CaseRow[x].containsPacMan())
                    newRect.setFill(Color.YELLOW);
                myBP.getChildren().add(newRect);
            }

        Circle circ = new Circle();
        circ.setRadius(2*(scaleFactor/2));
        circ.setFill(Color.GREEN);
        circ.setCenterX((pol.getCaseX()*scaleFactor)+100);
        circ.setCenterY((pol.getCaseY()*scaleFactor)+350);
        myBP.getChildren().add(circ);
    }

    public void DrawPlayspace(WritableImage img){
        terrain.setImage(img);
        terrain.setRotate(90);
        terrain.setScaleX(terrain.getScaleX()*3);
        terrain.setScaleY(terrain.getScaleY()*3);
    }

    public void DrawEntities(GestionnaireBoucles gb){
        bindPacman(ej.getPacman(), gb);
        AnimateurPacMan a = new AnimateurPacMan(pacman);
        gb.scheduleLoop(a,8);
        ArrayList<EntiteVue> ev = sm.getEntiteVues();
        for(EntiteVue e : ev){
            if(e.getSource() instanceof PacmanObject) {
                bindPacman((PacmanObject) e.getSource(), gb);
            }else if(e.getSource() instanceof Fantome){
                Animateur af = new Animateur(((EntiteVueAnimable)e).getSpriteAnimable());
                bindFantome((EntiteVueAnimable) e, gb);
                gb.scheduleLoop(af,200);
            }
        }
    }

    public void bindPacman(PacmanObject pac, GestionnaireBoucles gb){
        pacman.rotateProperty().bind(pac.pacAngleProperty());
        DeplaceurPacMan df = new DeplaceurPacMan(ej, pac);
        gb.scheduleLoop(df, 100);
        pacman.centerXProperty().bind(pac.getPositionLogique().ScaledXProperty());
        pacman.centerYProperty().bind(pac.getPositionLogique().ScaledYProperty());
    }

    public void bindFantome(EntiteVueAnimable s, GestionnaireBoucles gb){
        Fantome f = (Fantome) s.getSource();
        ObjectProperty<WritableImage> pr = s.getSpriteProperty();
        ImageView target  = getFantomeFromNom(f.getFantomeNom());

        target.imageProperty().bind(pr);
        target.xProperty().bind(f.getPositionLogique().CaseXProperty());
        target.yProperty().bind(f.getPositionLogique().CaseYProperty());
        f.getPositionLogique().forceUpdate();
        //DeplaceurFantome df = new DeplaceurFantome(ej, f);
        //gb.scheduleLoop(df, 100);
    }

    private ImageView getFantomeFromNom(FantomeNom fn){
        ImageView target = null;
        switch (fn){
            case BLINKY:
                target = blinky;
                break;
            case INKY:
                target = inky;
                break;
            case CLYDE:
                target = clyde;
                break;
            case PINKY:
                target = pinky;
                break;
            default:
                break;
        }
        return target;
    }

}
