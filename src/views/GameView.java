/**@author Yorick Geoffre
 * @brief contient les sources du code-behind de la vue de fin de jeu
 */

package views;

import javafx.application.Platform;
import javafx.beans.binding.*;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import model.Events.EventListener;
import model.Events.Events.EndGameEvent;
import model.Events.Events.Event;
import model.boucles.GestionnaireBoucles;
import model.entites.*;
import model.enums.FantomeNom;
import model.mouvement.Positions.PositionLogique;
import model.partie.CompteurScore;
import model.partie.CompteurVie;
import model.terrain.Case;
import model.terrain.EspaceDeJeu;
import views.viewClasses.Animateurs.Animateur;
import views.viewClasses.Animateurs.AnimateurPacMan;
import views.viewClasses.Sprites.SpriteManager;
import views.viewClasses.ViewEntities.EntiteVue;
import views.viewClasses.ViewEntities.EntiteVueAnimable;

import java.io.IOException;
import java.util.ArrayList;

/**
 * La vue principale du jeu, quand un niveau est chargé
 */
public class GameView implements EventListener {
    @FXML
    private Stage stage;
    @FXML private BorderPane myBP;
    @FXML private Pane gamePane;
    @FXML private Arc pacman;
    @FXML private Arc Life1;
    @FXML private Arc Life2;
    @FXML private Arc Life3;
    @FXML private ImageView blinky;
    @FXML private ImageView pinky;
    @FXML private ImageView inky;
    @FXML private ImageView clyde;
    @FXML private ImageView terrain;
    @FXML private Label scoreCounter;

    @FXML private ImageView[] fantomes = {blinky,pinky,inky,clyde};
    private final int scaleFactor = 12;
    private SpriteManager sm = null;
    private EspaceDeJeu ej = null;
    @FXML
    public void initialize(){
    }

    public void receiveStage(Stage s){
        stage = s;
    }

    /**
     * Va instancier le spriteManager et charger toutes les ressources graphiques
     * @param ej l'espace de jeu actuel
     */
    public void loadRessources(EspaceDeJeu ej){
        sm = new SpriteManager(ej); //charge aussi toutes les données de tous les sprites
        this.ej = ej;
        DrawPlayspace(sm.getTerrainBackground());
    }

    /**
     * Une mode de dessin de la carte et des entités STRICTEMENT pour le débogage, qui est assez sale et peu efficace car
     * il va parcourir la totalité du terrain à chaque tick... mais il fonctionne
     * vraiment juste pour débugger
     */
    public void DrawCollisionMapDebug(){
        gamePane.getChildren().removeIf(n -> (n instanceof Rectangle) || (n instanceof Circle));
        Pacman pac = ej.getPacman();
        if(pac == null)
            return;
        PositionLogique pol = pac.getPositionLogique();

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
                    newRect.setFill(Color.FIREBRICK);
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
                    if(case1.getStaticEntite() instanceof Fruits)
                        gum.setFill(Color.RED);
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

    /**
     * Va dessiner l'arrière-plan du terrain
     * @param img l'image à dessiner
     */
    public void DrawPlayspace(WritableImage img){
        terrain = new ImageView();
        myBP.getChildren().add(terrain);
        terrain.setImage(img);
        terrain.setRotate(90); /*correction du bug précisé dans la doc de assemblePlayspace()*/
        terrain.setX(300);
        terrain.setY(150);
        terrain.setScaleX(1.5);
        terrain.setScaleY(1.5);
        terrain.toBack();
    }

    /**
     * Va dessiner les différentes entités sur e terrain, en les bindant
     * @param gb
     */
    public void DrawEntities(GestionnaireBoucles gb) {
        gb.scheduleLoop(()->{Platform.runLater(()->{stage.requestFocus();});},50);
        bindPacman(ej.getPacman());
        AnimateurPacMan a = new AnimateurPacMan(pacman);
        gb.scheduleLoop(a, 8);
        ArrayList<EntiteVue> ev = sm.getEntiteVues();
        for (EntiteVue e : ev) {
            if (e.getSource() instanceof Pacman) {
                bindPacman((Pacman) e.getSource());
            } else if (e.getSource() instanceof Fantome) {
                Animateur af = new Animateur(((EntiteVueAnimable) e).getSpriteAnimable());
                bindFantome((EntiteVueAnimable) e, gb);
                gb.scheduleLoop(af, 200);
            }
        }
        Case cases[][] = ej.getStage();
        for (int x = 0; x < cases[0].length; x++)
            for (int y = 0; y < cases.length; y++) {
                Case[] CaseRow = cases[y];
                Case case1 = CaseRow[x];
                if(case1.hasStaticEntities() && case1.getStaticEntite() instanceof Mangeable){
                    Entite e = case1.getStaticEntite();
                    Circle c = new Circle();
                    c.setFill(Color.YELLOW);
                    IntegerBinding xpos = e.getPositionLogique().CaseColProperty().multiply(scaleFactor).add(62);
                    IntegerBinding ypos = e.getPositionLogique().CaseRowProperty().multiply(scaleFactor).add(72);
                    c.visibleProperty().bind(e.isVisibleProperty());
                    e.setVisible(true);
                    c.centerXProperty().bind(xpos);
                    c.centerYProperty().bind(ypos);
                    c.setRadius(2);
                    if(e instanceof Fruits)
                        c.setFill(Color.RED);

                    if(e instanceof SuperGomme)
                        c.setRadius(4);

                    gamePane.getChildren().add(c);
                }
            }
    }

    public void bindCompteurs(CompteurScore cs, CompteurVie cv){
        BooleanBinding alive1 = cv.Viesproperty().greaterThanOrEqualTo(1);
        BooleanBinding alive2 = cv.Viesproperty().greaterThanOrEqualTo(2);
        BooleanBinding alive3 = cv.Viesproperty().greaterThanOrEqualTo(3);



        Life1.visibleProperty().bind(alive3);
        Life2.visibleProperty().bind(alive2);
        Life3.visibleProperty().bind(alive1);

        StringBinding sb = cs.Scoreproperty().asString();
        scoreCounter.textProperty().bind(Bindings.format("Score: %s",sb));
    }

    public void bindPacman(Pacman pac){
        pacman.setLayoutX(terrain.getLayoutX());
        pacman.setLayoutY(terrain.getLayoutY());
        pacman.rotateProperty().bind(pac.pacAngleProperty());
        IntegerBinding xpos = pac.getPositionLogique().CaseColProperty().multiply(scaleFactor).add(58).add(2);
        IntegerBinding ypos = pac.getPositionLogique().CaseRowProperty().multiply(scaleFactor).add(68).add(2);
        ObjectBinding<Color> paintProperty = Bindings.when(pac.SuperPacmanProperty()).then(Color.RED).otherwise(Color.YELLOW);
        pacman.fillProperty().bind(paintProperty);
        pacman.centerXProperty().bind(xpos);
        pacman.centerYProperty().bind(ypos);
    }

    public void bindFantome(EntiteVueAnimable s, GestionnaireBoucles gb){
        Fantome f = (Fantome) s.getSource();
        ObjectProperty<WritableImage> pr = s.getSpriteProperty();
        ImageView target  = new ImageView();
        gamePane.getChildren().add(target);
        target.setLayoutX(terrain.getLayoutX());
        target.setLayoutY(terrain.getLayoutY());
        target.imageProperty().bind(pr);

        IntegerBinding xpos = f.getPositionLogique().CaseColProperty().multiply(scaleFactor).add(58).subtract(2);
        IntegerBinding ypos = f.getPositionLogique().CaseRowProperty().multiply(scaleFactor).add(68).subtract(6);

        target.xProperty().bind(xpos);
        target.yProperty().bind(ypos);
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

    private void EndGame(){
        myBP.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameOver.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameOverView view = loader.getController();
        view.receiveStage(stage);
        view.passScore(scoreCounter.getText());
        Scene scene = new Scene(root);
        stage.setTitle("pacmanFX - Game Over");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void HandleEvent(Event e) {
        if(e instanceof EndGameEvent){
            Platform.runLater(() -> {EndGame();});
        }
    }
}
