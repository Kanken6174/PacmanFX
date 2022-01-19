package views;

import javafx.beans.binding.DoubleBinding;
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
import model.entites.Pacman;
import model.enums.FantomeNom;
import model.mouvement.Positions.PositionLogique;
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
        //myBP.getChildren().removeIf(n -> (n instanceof Rectangle) || (n instanceof Circle));
        myBP.getChildren().clear();
        PositionLogique pol = ej.getPacman().getPositionLogique();
        //System.out.println("Pacman posl at:"+pol.getCaseX()+" "+pol.getCaseY());
        int scaleFactor = 12;

        Case cases[][] = ej.getStage();
        for(int x = 0; x < cases[0].length; x++)
            for (int y = 0; y < cases.length ; y++) {
                Case[] CaseRow = cases[y];
                Case case1 = CaseRow[x];
                Rectangle newRect = new Rectangle();
                newRect.setX((x*scaleFactor)+100);
                newRect.setY((y*scaleFactor)+450);
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
                myBP.getChildren().add(newRect);

                if(case1 != null && case1.hasStaticEntities()) {
                    Circle gum = new Circle();
                    gum.setRadius(4);
                    gum.setFill(Color.YELLOW);
                    gum.setCenterX((x*scaleFactor)+100+4);
                    gum.setCenterY((y*scaleFactor)+450+4);
                    myBP.getChildren().add(gum);
                }
            }

        Circle circ = new Circle();
        circ.setRadius(4);
        circ.setFill(Color.GREEN);
        circ.setCenterX((pol.getCaseY()*scaleFactor)+100+8);
        circ.setCenterY((pol.getCaseX()*scaleFactor)+450+8);
        myBP.getChildren().add(circ);
    }

    public void DrawPlayspace(WritableImage img){
        terrain.setImage(img);
        terrain.setRotate(90);
        terrain.setScaleX(terrain.getScaleX()*2);
        terrain.setScaleY(terrain.getScaleY()*2);
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

    public void bindPacman(Pacman pac){
        pacman.rotateProperty().bind(pac.pacAngleProperty());
        DoubleBinding xbind = (DoubleBinding) pac.getPositionLogique().CaseXProperty().add(pac.getPositionGraphique().xProperty()).multiply(10d);
        DoubleBinding ybind = (DoubleBinding) pac.getPositionLogique().CaseYProperty().add(pac.getPositionGraphique().yProperty()).multiply(10d);
        pacman.centerXProperty().bind(xbind);
        pacman.centerYProperty().bind(ybind);
    }

    public void bindFantome(EntiteVueAnimable s, GestionnaireBoucles gb){
        Fantome f = (Fantome) s.getSource();
        ObjectProperty<WritableImage> pr = s.getSpriteProperty();
        ImageView target  = getFantomeFromNom(f.getFantomeNom());

        target.imageProperty().bind(pr);

        DoubleBinding xbind = (DoubleBinding) f.getPositionLogique().CaseXProperty().add(f.getPositionGraphique().xProperty()).multiply(10d);
        DoubleBinding ybind = (DoubleBinding) f.getPositionLogique().CaseYProperty().add(f.getPositionGraphique().yProperty()).multiply(10d);
        target.xProperty().bind(xbind);
        target.yProperty().bind(ybind);
        f.getPositionLogique().forceUpdate();
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
