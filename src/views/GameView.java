package views;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.boucles.GestionnaireBoucles;
import model.entites.Fantome;
import model.entites.Pacman;
import model.enums.FantomeNom;
import model.mouvement.Positions.PositionLogique;
import model.partie.CompteurScore;
import model.terrain.Case;
import model.terrain.EspaceDeJeu;
import views.viewClasses.Animateurs.Animateur;
import views.viewClasses.Animateurs.AnimateurPacMan;
import views.viewClasses.Sprites.SpriteManager;
import views.viewClasses.ViewEntities.EntiteVue;
import views.viewClasses.ViewEntities.EntiteVueAnimable;

import java.util.ArrayList;

public class GameView {
    @FXML
    private Stage stage;
    @FXML private BorderPane myBP;
    @FXML private Pane gamePane;
    @FXML private Arc pacman;
    @FXML private ImageView blinky;
    @FXML private ImageView pinky;
    @FXML private ImageView inky;
    @FXML private ImageView clyde;
    @FXML private ImageView terrain;
    @FXML private Label scoreCounter;

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
    }

    public void DrawCollisionMapDebug(){
        gamePane.getChildren().removeIf(n -> (n instanceof Rectangle) || (n instanceof Circle));
        Pacman pac = ej.getPacman();
        if(pac == null)
            return;
        PositionLogique pol = pac.getPositionLogique();
        int scaleFactor = 12;

        Case cases[][] = ej.getStage();
        for(int x = 0; x < cases[0].length; x++)
            for (int y = 0; y < cases.length ; y++) {
                Case[] CaseRow = cases[y];
                Case case1 = CaseRow[x];
                Rectangle newRect = new Rectangle();
                newRect.setX((x*scaleFactor)+100);
                newRect.setY((y*scaleFactor)+100);
                newRect.setHeight(8*scaleFactor);
                newRect.setWidth(8*scaleFactor);
                newRect.setSmooth(false);
                if(case1 != null && case1.isObstacle())
                    newRect.setFill(Color.RED);
                else
                    newRect.setFill(Color.BLACK);
                if(case1 != null && case1.hasEntities() && !case1.containsPacMan())
                    newRect.setFill(Color.BLUE);
                if(case1 != null && case1.containsPacMan())
                    newRect.setFill(Color.YELLOW);
                gamePane.getChildren().add(newRect);

                if(case1 != null && case1.hasStaticEntities()) {
                    Circle gum = new Circle();
                    gum.setRadius(4);
                    gum.setFill(Color.YELLOW);
                    gum.setCenterX((x*scaleFactor)+100+4);
                    gum.setCenterY((y*scaleFactor)+100+4);
                    gamePane.getChildren().add(gum);
                }
            }

        Circle circ = new Circle();
        circ.setRadius(4);
        circ.setFill(Color.GREEN);
        circ.setCenterX((pol.getCaseColumn()*scaleFactor)+100+8);
        circ.setCenterY((pol.getCaseRow()*scaleFactor)+100+8);
        gamePane.getChildren().add(circ);
    }

    public void DrawPlayspace(WritableImage img){
        terrain.setImage(img);
        terrain.setRotate(90);
        terrain.setScaleX(1.7);
        terrain.setScaleY(1.7);
    }

    public void DrawEntities(GestionnaireBoucles gb){
        bindPacman(ej.getPacman());
        AnimateurPacMan a = new AnimateurPacMan(pacman);
        gb.scheduleLoop(a,8);
        ArrayList<EntiteVue> ev = sm.getEntiteVues();
        for(EntiteVue e : ev){
            if(e.getSource() instanceof Pacman) {
                bindPacman((Pacman) e.getSource());
            }else if(e.getSource() instanceof Fantome){
                Animateur af = new Animateur(((EntiteVueAnimable)e).getSpriteAnimable());
                bindFantome((EntiteVueAnimable) e, gb);
                gb.scheduleLoop(af,200);
            }
        }
    }

    public void bindCompteur(CompteurScore cs){
        StringBinding sb = cs.Scoreproperty().asString();
        scoreCounter.textProperty().bind(Bindings.format("Score: %s",sb));
    }

    public void bindPacman(Pacman pac){
        pacman.setLayoutX(terrain.getLayoutX());
        pacman.setLayoutY(terrain.getLayoutY());
        pacman.rotateProperty().bind(pac.pacAngleProperty());
        DoubleBinding pacxbind = (DoubleBinding) pac.getPositionLogique().CaseColProperty()/*.add(pac.getPositionGraphique().yProperty())*/.multiply(40d);
        DoubleBinding pacybind = (DoubleBinding) pac.getPositionLogique().CaseRowProperty()/*.add(pac.getPositionGraphique().xProperty())*/.multiply(40d);
        pacman.centerXProperty().bind(pacxbind);
        pacman.centerYProperty().bind(pacybind);
    }

    public void bindFantome(EntiteVueAnimable s, GestionnaireBoucles gb){
        Fantome f = (Fantome) s.getSource();
        ObjectProperty<WritableImage> pr = s.getSpriteProperty();
        ImageView target  = getFantomeFromNom(f.getFantomeNom());
        target.setLayoutX(terrain.getLayoutX());
        target.setLayoutY(terrain.getLayoutY());
        target.imageProperty().bind(pr);

        DoubleBinding xbind = (DoubleBinding) f.getPositionLogique().CaseRowProperty()/*.add(f.getPositionGraphique().xProperty())*/.multiply(terrain.getScaleX()*8);
        DoubleBinding ybind = (DoubleBinding) f.getPositionLogique().CaseColProperty()/*.add(f.getPositionGraphique().yProperty())*/.multiply(terrain.getScaleY()*8);
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
