package views;

import controller.GameController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.stage.Stage;
import model.fileData.LevelFile;
import tools.files.ArchivedScore;
import tools.files.FileUtils;
import tools.files.ScoreSaver;

import java.io.IOException;
import java.util.ArrayList;

public class MenuView{
    @FXML private Parent root;
    @FXML private Stage stage;
    @FXML private Button PlyBtn;

    @FXML
    private ComboBox<LevelFile> levelsList;
    @FXML
    private ListView<String> ScoreList;

    @FXML private Label BoardTitle;

    private ListProperty<LevelFile> levels = new SimpleListProperty<LevelFile>();

    @FXML
    public void initialize(){
        PlyBtn.disableProperty().bind(levelsList.valueProperty().isNull());

        levels = FileUtils.discoverFiles();

        levelsList.itemsProperty().bindBidirectional(levels);

        levelsList.setCellFactory(__ ->
                new ComboBoxListCell<LevelFile>(){

                    @Override
                    public void updateItem(LevelFile item, boolean empty) {
                        textProperty().unbind();
                        textProperty().set("test");
                        super.updateItem(item, empty);
                        if (!empty) {
                            textProperty().bind(Bindings.format("Nom du niveau: %s ,taille: %d x %d", item.FilenameProperty(), item.RowProperty(),item.ColumnProperty()));
                        } else {
                            textProperty().unbind();
                            setText("");
                        }
                    }
                }
        );
        levelsList.getSelectionModel().select(0);
        selectionChanged(new ActionEvent());
        BoardTitle.textProperty().bind(Bindings.format("Leaderboard du niveau: %s",levelsList.valueProperty().getValue().getFilename()));
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

        GameView view = loader.getController();
        view.receiveStage(stage);
        Scene scene = new Scene(root);
        stage.setTitle("pacmanFX - en jeu");
        stage.setScene(scene);

        stage.show();
        LevelFile toLoad = levelsList.getValue();
        GameController gm = new GameController(view, toLoad);
        root.setOnKeyPressed(gm);   //ça devrait nous éviter d'avoir à demander le focus à chaque fois
        root.requestFocus();
    }

    public void selectionChanged(ActionEvent actionEvent) {
        ScoreList.getItems().clear();
        ArrayList<ArchivedScore> scores = ScoreSaver.GetScoresForMap(levelsList.getValue().getFilename());
        for(ArchivedScore score : scores) {
            String toAdd = new String(score.getPlayerName()+" | "+score.getScore()+" points");
            ScoreList.getItems().add(toAdd);
        }
    }
}
