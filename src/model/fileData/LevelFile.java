/**@author Yorick Geoffre
 * @brief Ce fichier contient les sources du LevelFile*/

package model.fileData;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Un levelFile est une structure de données qui décrit la présence d'un niveau pacmanFX valide dans un chemin spécifique,
 * avec la taille du fichier texte en colonnes et lignes, et le nom du fichier lui-même.
 *
 * Les propriétés ont été ajoutées afin de pouvoir implémenter une cellFactory plus tard, mais comme ces valeurs ne
 * changeront pas, elle ne sont pas vraiment nécessaires.
 */
public class LevelFile {
    /** le nom du fichier de niveau  que ce LevelFile décrit, pour être valide, le dossier qui porte ce nom doit avoir
     * un fichier .collisionMap, .entityMap et un .score optionnel avec le même nom que le dossier.*/
    private StringProperty filename = new SimpleStringProperty();
        public String getFilename() {return filename.get();}
        public StringProperty FilenameProperty() {return filename;}
        public void setFilename(String filename) {this.filename.set(filename);}

    /**Le nombre de lignes maximum trouvé parmi les fichiers .enetityMap et .collisionMap*/
    private IntegerProperty rowAmount = new SimpleIntegerProperty();
        public int getRowAmount() {return rowAmount.get();}
        public IntegerProperty RowProperty() {return rowAmount;}
        public void setRowAmount(int rows) {this.rowAmount.set(rows+1);}

    /**Le nombre de colonnes maximum trouvé parmi les fichiers .enetityMap et .collisionMap*/
    private IntegerProperty columnAmount = new SimpleIntegerProperty();
        public int getColumnAmount() {return columnAmount.get();}
        public IntegerProperty ColumnProperty() {return columnAmount;}
        public void setColumnAmount(int columns) {this.columnAmount.set(columns+1);}

    /**
     * Le constructeur du levelFile
     * @param filename le nom du dossier dans lequel les fichiers requis sont présents
     * @param rows  le nombre de lignes maximum trouvé parmi les fichiers
     * @param columns le nombre de colonnes maximum trouvé parmi les fichiers
     */
    public LevelFile(String filename, int rows, int columns){
        this.filename.set(filename);
        this.rowAmount.set(rows);
        this.columnAmount.set(columns);
    }
}