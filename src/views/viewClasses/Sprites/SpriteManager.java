/**@author Yorick Geoffre
 * @brief contient les sources du Sprite Manager
 */

package views.viewClasses.Sprites;

import javafx.scene.image.WritableImage;
import model.entites.Entite;
import model.entites.Fantome;
import model.fileData.SpriteAnchor;
import model.terrain.Case;
import model.terrain.EspaceDeJeu;
import views.viewClasses.Loaders.ImageLoader;
import views.viewClasses.Loaders.ImageMaster;
import views.viewClasses.Loaders.TerrainBackgroundSpriteMaker;
import views.viewClasses.ViewEntities.EntiteVue;
import views.viewClasses.ViewEntities.EntiteVueAnimable;

import java.util.ArrayList;

/**
 * Le sprite manager possède toutes les sprites et s'occupe des actions qui y sont liées. Du chargement au traitement elles
 * restent dans le SpriteManager et sont ensuite bindées ou simplement envoyées vers une ImageView de vue.*/
public class SpriteManager {
    /**La ressource qui correspond à l'arrière plan du terrain, déjà assemblé par le constructeur*/
    private WritableImage TerrainBackground;
    /**La collection des entiteVues (ref. entite modèle + sprite)*/
    private ArrayList<EntiteVue> entiteVues = new ArrayList<EntiteVue>();
    /**La collection des différents SpriteSheets (ressources contenant toutes les sprites d'un type d'entite donné, selon SpriteAnchor)*/
    private ArrayList<ImageMaster> SpriteSheetsMasters = new ArrayList<ImageMaster>();  //contient tous les spritesheets, privé

    /**
     * Le constructeur standard du SpriteManager
     * @param ej l'espace de jeu du modèle
     */
    public SpriteManager(EspaceDeJeu ej){
        entiteVues = makeEntiteVuesFromEntites(ej.getAllEntites());
        Case[][] cases = ej.getStage();
        Sprite[][] sprites = TerrainBackgroundSpriteMaker.makeCellSprites(cases,cases.length, cases[0].length);
        TerrainBackground = TerrainBackgroundSpriteMaker.assemblePlayspace(sprites, sprites.length, sprites[0].length);
    }

    /**
     * Va créer les EntitéVues à partir des entités du modèle, en prenant les informations de leur SpriteAnchor pour assembler la sprite correspondante
     * @param allEntites les entités du modèle
     * @return une collection d'entiteVue qui serotn stockées localement
     */
    private ArrayList<EntiteVue> makeEntiteVuesFromEntites(ArrayList<Entite> allEntites){
        ArrayList<EntiteVue> EVs = new ArrayList<>();

        for(Entite e : allEntites){
            SpriteAnchor sp = e.getSpriteAnchor();
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

    /**
     * Va vérifier si il existe déjà un ImageMaster dont la ressource (spritesheet) est celui pointé par la SpriteAnchor
     * @param sp la spriteAnchor qui contient les informations d'assemblage de sprite
     * @return L'imageMaster requis pour assembler cette sprite, instancié ou récupéré depuis la collection locale
     */
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