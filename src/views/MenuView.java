package views;

import controller.GameController;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.stage.Stage;
import model.fileData.LevelFile;
import tools.files.FileUtils;

import java.io.IOException;

public class MenuView{
    @FXML private Parent root;
    @FXML private Stage stage;
    @FXML private Button PlyBtn;

    @FXML
    private ComboBox<LevelFile> levelsList;


    private ListProperty<LevelFile> levels = new SimpleListProperty<LevelFile>();

    @FXML
    public void initialize(){
        PlyBtn.disableProperty().bind(levelsList.valueProperty().isNull());

        levels = FileUtils.discoverFiles();

        levelsList.itemsProperty().bindBidirectional(levels);

        levelsList.setCellFactory(__ ->
                new ComboBoxListCell<>(){
                    @Override
                    public void updateItem(LevelFile item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            //textProperty().bind(Bindings.format("Nom du niveau: %s Colonnes: %d Lignes: %d", item.FilenameProperty(), item.ColumnProperty(), item.RowProperty()));
                        } else {
                            textProperty().unbind();
                            setText("");
                        }
                    }
                }
        );


    }

    @FXML
    public void MenuClicked(){
    }

    public void receiveStage(Stage stage){
        this.stage = stage;
    }

    public void PlayClicked(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameView view = loader.getController();

        Scene scene = new Scene(root);
        stage.setTitle("pacmanFX - en jeu");
        stage.setScene(scene);

        stage.show();
        LevelFile toLoad = levelsList.getValue();
        GameController gm = new GameController(view, toLoad);
        root.setOnKeyPressed(gm);   //ça devrait nous éviter d'avoir à demander le focus à chaque fois
        root.requestFocus();
    }
}
