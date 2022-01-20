/**
 * @author Yorick geoffre
 * @brief Ce fichier contient les sources du launcher, le point d'entrée de l'application
 */

package launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.MenuView;

/**
 * Le launcher est le point d'entrée de l'application, il va instancier la vue du Menu
 * via son fichier FXML
 */
public class Launcher extends Application{

    /**
     * Le point d'entrée
     * @param stage le stage de la fenêtre
     * @throws Exception
     */
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu.fxml"));
        Parent root = loader.load();

        MenuView view = loader.getController();
        root.requestFocus();
        Scene scene = new Scene(root);
        stage.setTitle("pacmanFX");
        stage.setWidth(800);
        stage.setHeight(700);
        stage.setScene(scene);

        stage.show();
        view.receiveStage(stage);
        root.requestFocus();    //on le fait avant de perdre l'accès au root
    }

}