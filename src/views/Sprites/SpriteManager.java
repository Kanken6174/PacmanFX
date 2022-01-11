package views.Sprites;

import javafx.scene.image.WritableImage;
import model.entites.Entite;
import model.terrain.EspaceDeJeu;
import views.ViewEntities.EntiteVue;

import java.util.ArrayList;

public class SpriteManager {
    private WritableImage TerrainBackground;
    private ArrayList<EntiteVue> entiteVues;

    public SpriteManager(EspaceDeJeu ej){
        entiteVues = makeEntiteVuesFromEntites(ej.getAllEntites());
    }

    private ArrayList<EntiteVue> makeEntiteVuesFromEntites(ArrayList<Entite> allEntites){
        ArrayList<EntiteVue> EVs = new ArrayList<>();



        return EVs;
    }
}