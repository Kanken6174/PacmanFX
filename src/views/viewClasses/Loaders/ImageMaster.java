/**@author Yorick Geoffre
 * @brief contient les sources de l'Image master
 */

package views.viewClasses.Loaders;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import model.enums.FantomeNom;
import tools.ImageClipper;
import tools.ImageConverter;

/**
 * L'imageMaster sert à obtenir des parties d'images depuis une ressource donnée, qui lui a été passée lors de sa création.
 * Il est dont le "master" de cette ressource
 */
public class ImageMaster {
    /**La spritesheet contenue dans cet ImageMaster*/
    private Image MainRessource;

    /**
     * @deprecated l'ancien constructeur de l'imageMaster, qui prend la ressource par défaut
     */
    public ImageMaster(){
       MainRessource = ImageConverter.ImageToWriteableImage(ImageLoader.loadRessources());
    }

    /**
     * Ce constructeur de l'ImageMaster prend sa ressource en argument
     * @param MainRessource la ressource à passer à
     */
    public ImageMaster(Image MainRessource){
        this.MainRessource = MainRessource;
    }

    /**
     * @deprecated l'ancienne méthode qui permettait d'obtenir une image à un point XY sans préciser la taille
     * @param x dimension X
     * @param y dimension Y
     * @return l'image découpée
     */
    public Image getImageAt(int x, int y){
        Image img = ImageClipper.clipSprite(ImageLoader.loadRessources(),x,y);
        return img;
    }

    /**
     * Identique à getImageAt mais avec une hauteur et largeur spécifiée
     * @param x dimension X
     * @param y dimension Y
     * @param width largeur
     * @param height hauteur
     * @return l'image découpée
     */
    public WritableImage getSpritePart(int x, int y, int width, int height){
        WritableImage bi = null;
        try{
            bi = ImageClipper.clip(MainRessource,x,y,width,height);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("clip error");
        }
        return bi;
    }

    /**
     * Permet de récupérer une tile du terrain souhaitée (taille 8x8)
     * @param Xtile
     * @param Ytile
     * @return
     */
    public WritableImage getTerrainTiles(int Xtile, int Ytile){
        int x = 228+(8*Xtile);//début des tiles de terrain à 228:0
        int y = 0+(8*Ytile);
        int height = 8;
        int width = 8;

        WritableImage bi = null;

        bi = getSpritePart(x,y,width,height);

        return bi;
    }

    /**@deprecated les SpriteAnchors rendent cette méthode obsolète
     * @return la sprite effrayée des fantômes (bleu)
     */
    public WritableImage getSpritesheetGhostFreightened(){
        int x = 456+(16*8);
        int y = 64;
        int height = 16;
        int width = 16*2;   //que 2 sprites pour celui-là

        WritableImage bi = null;

        bi = getSpritePart(x,y,width,height);

        return bi;
    }

    /**
     * @deprecated les SpriteAnchors rendent cette méthode obsolète
     * @param fn le nom du fantome (enum)
     * @return la spritesheet pour ce fantôme spécifique
     */
    public WritableImage getSpritesheetForGhost(FantomeNom fn){
        int x = 456;
        int y = 64;
        int height = 16;
        int width = 16*8;

        y += fn.ordinal()*16;   //on récupère ce fantôme en particulier

        WritableImage bi = null;

        bi = getSpritePart(x,y,width,height);

        return bi;
    }

    public Image getMainRessource() {
        return MainRessource;
    }
}