/**@Author Yorick Geoffre
 * @brief contient les sources des utilitaires d'édition d'image
 */

package tools;


import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

/**
 * Cette classe contient les utilitaires permettant la modification d'une image avant de l'afficher dans les vues
 * (modification directe de l'image à travers la classe WritableImage)
 */
public class ImageClipper {
    /**
     * @param source l'image source
     * @param x une coordonnée X sur l'image source
     * @param y une coordonnée Y sur l'image source
     * @return une sous-image de la source, découpée à X et Y avec une largeur et hauteur de 16 (sprite entité standard)
     */
    public static WritableImage clipSprite(Image source, int x, int y){
        PixelReader px = source.getPixelReader();
        WritableImage wi = new WritableImage(px,x,y,16,16);
        return wi;
    }

    /**
     * @param source l'image source
     * @param x une coordonnée X sur l'image source
     * @param y une coordonnée Y sur l'image source
     * @param h la hauteur de la surface que l'on veut conserver
     * @param w la largeur de la surface que l'on veut conserver
     * @return une sous-image de la source, découpée à X et Y avec largeur w et hauteur h
     */
    public static WritableImage clip(Image source, int x, int y, int w, int h){
        PixelReader px = source.getPixelReader();
        WritableImage wi = new WritableImage(px,x,y,w,h);
        return wi;
    }

    /**
     * Utilisé par les animateurs de fantômes
     *
     * @deprecated plus utilisé depuis l'ajout des SpriteAnchors
     *
     * @param source une spritesheet
     * @param id l'id du fantôme (pour savoir où découper)
     * @return la spritesheet d'animation du fantôme (8 images en une)
     */
    public static WritableImage clipGhostAnimation(Image source, int id){
        PixelReader px = source.getPixelReader();
        WritableImage wi = new WritableImage(px,441,49+(16*id),14,14*8);
        return wi;
    }

    /**
     * Permet de récupérer une partie d'une spritesheet d'animation (ou une "frame" spécifique)
     * @param source la spritesheet d'animation, dont chaque frame devrait être un carré (sinon déformation)
     * @param id l'id de la frame à sélectionner
     * @return la frame sélectionnée sur la spritesheet d'animation
     */
    public static WritableImage getFrame(Image source, int id){
        PixelReader px = source.getPixelReader();
        WritableImage wi = new WritableImage(px,id,0,(int)source.getHeight(),(int)source.getHeight());
        return wi;
    }

    /**
     * Permet de récupérer une partie d'une spritesheet d'animation (ou une "frame" spécifique)
     * @deprecated version avec côté précisé, plus utilisé
     * @param source la spritesheet d'animation, dont chaque frame devrait être un carré (sinon déformation)
     * @param id l'id de la frame à sélectionner
     * @param sideSize la taille de côté précisée
     * @return la frame sélectionnée sur la spritesheet d'animation
     */
    public static WritableImage getFrame(Image source, int id, int sideSize){
        PixelReader px = source.getPixelReader();
        WritableImage wi = new WritableImage(px,id*sideSize,0,sideSize,sideSize);
        return wi;
    }
}