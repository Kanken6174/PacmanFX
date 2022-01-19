/**@author Yorick Geoffre
 * @brief Ce fichier contient les sources du SpriteAnchor*/

package model.fileData;

/**Un SpriteAnchor est une structure de données qui contiendra les informations nécessaires aux classes des vues pour trouver et
 * générer la sprite d'une entité ou d'un décor (case).*/
public class SpriteAnchor {
    private int x = 0;
    private int y = 0;
    private int rotation = 0;
    private String path = "";
    private int width = 8;
    private int height = 8;
    private boolean isAnimated = false;

    /**
     * Le constructeur du SpriteAnchor
     * @param x la position du sprite sur la spritesheet (x)
     * @param y la position du sprite sur la spritesheet (y)
     * @param rotation  la rotation nécessaire à appliquer au sprite (multiple de 90)
     * @param path      le chemin vers le fichier qui contient le spritesheet sur lequel ce sprite est présent
     * @param height    la hauteur du sprite
     * @param width     la largeur du sprite
     * @param isAnimated  si le sprite est animé ou non (les fantômes le sont).
     */
    public SpriteAnchor(int x, int y, int rotation, String path, int height, int width, boolean isAnimated){
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.path = path;
        this.height = height;
        this.width = width;
        this.isAnimated = isAnimated;
    }

    public int getHeight() {
        return height;
    }

    public boolean isAnimated() {
        return isAnimated;
    }

    public int getRotation() {
        return rotation;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getPath() {
        return path;
    }
}
