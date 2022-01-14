package launcher;

import controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.boucles.wrong.GestionnaireBoucles;
import views.gameView;

public class Launcher extends Application{

    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game.fxml"));

        Parent root = loader.load();
        gameView view = loader.getController();
        GameController gm = new GameController(view);

        root.setOnKeyPressed(gm);   //ça devrait nous éviter d'avoir à demander le focus à chaque fois
        root.requestFocus();
        GestionnaireBoucles gb = new GestionnaireBoucles();
        gb.schedule(new Runnable(){@Override public void run(){root.requestFocus();}},100);
        Scene scene = new Scene(root);

        stage.setTitle("pacmanFX");
        stage.setWidth(800);
        stage.setHeight(700);
        stage.setScene(scene);

        stage.show();
        gb.run();
        root.requestFocus();    //on le fait avant de perdre l'accès au root
    }

}