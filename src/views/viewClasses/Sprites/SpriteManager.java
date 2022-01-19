package views.viewClasses.Sprites;

import javafx.scene.image.WritableImage;
import model.entites.Entite;
import model.entites.Fantome;
import model.fileData.SpriteAnchor;
import model.terrain.Case;
import model.terrain.EspaceDeJeu;
import views.viewClasses.Loaders.ImageLoader;
import views.viewClasses.Loaders.ImageMaster;
import views.viewClasses.Loaders.spriteLoader;
import views.viewClasses.ViewEntities.EntiteVue;
import views.viewClasses.ViewEntities.EntiteVueAnimable;

import java.util.ArrayList;

public class SpriteManager {
    private WritableImage TerrainBackground;
    private ArrayList<EntiteVue> entiteVues = new ArrayList<EntiteVue>();
    private ArrayList<ImageMaster> SpriteSheetsMasters = new ArrayList<ImageMaster>();  //contient tous les spritesheets, privé


    public SpriteManager(EspaceDeJeu ej){
        entiteVues = makeEntiteVuesFromEntites(ej.getAllEntites());
        Case[][] cases = ej.getStage();
        Sprite[][] sprites = spriteLoader.makeCellSprites(cases,cases.length, cases[0].length);
        TerrainBackground = spriteLoader.assemblePlayspace(sprites, sprites.length, sprites[0].length);
    }

    private ArrayList<EntiteVue> makeEntiteVuesFromEntites(ArrayList<Entite> allEntites){
        ArrayList<EntiteVue> EVs = new ArrayList<>();

        for(Entite e : allEntites){
            SpriteAnchor sp = e.getSp();
            EntiteVue ev = null;
            if(sp!=null) {
                ImageMaster im = getSpritesheetMasterOrLoad(sp); //désormais, im contient l'imageMaster qui possède l'image voulue

                Sprite s = null;
                if (sp.isAnimated()) {
                    if (e instanceof Fantome)
                        s = new SpriteAnimable(im.getSpritePart(sp.getX(), sp.getY()+64, sp.getWidth(), sp.getHeight()), 2, 4);
                    else {
                    }//les autres entité ici, pour l'instant il y a que le fantome qui utilise des sprites animées
                    ev = new EntiteVueAnimable((SpriteAnimable) s,e);
                } else {  //pas animé
                    s = new Sprite(im.getSpritePart(sp.getX(), sp.getY(), sp.getWidth(), sp.getHeight()));
                    ev = new EntiteVue(s,e);
                }
                EVs.add(ev);
            }
        }

        return EVs;
    }

    private ImageMaster getSpritesheetMasterOrLoad(SpriteAnchor sp){
        if(SpriteSheetsMasters == null){
            ImageMaster im = new ImageMaster(ImageLoader.getImageFromPath(sp.getPath()));
            SpriteSheetsMasters.add(im);
            return im;
        }
        for(ImageMaster im : SpriteSheetsMasters){
            if(im.getMainRessource().getUrl() == sp.getPath())
                return im;
        }
        //pas trouvé, on charge
        ImageMaster im = new ImageMaster(ImageLoader.getImageFromPath(sp.getPath()));
        SpriteSheetsMasters.add(im);
        return im;
    }

    public WritableImage getTerrainBackground() {
        return TerrainBackground;
    }

    public ArrayList<EntiteVue> getEntiteVues() {
        return entiteVues;
    }
}