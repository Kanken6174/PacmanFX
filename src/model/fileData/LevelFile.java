package model.fileData;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LevelFile {
    private StringProperty filename = new SimpleStringProperty();
        public String getFilename() {return filename.get();}
        public StringProperty FilenameProperty() {return filename;}
        public void setFilename(String filename) {this.filename.set(filename);}

    private IntegerProperty rowAmount = new SimpleIntegerProperty();
        public int getRowAmount() {return rowAmount.get();}
        public IntegerProperty RowProperty() {return rowAmount;}
        public void setRowAmount(int rows) {this.rowAmount.set(rows+1);}

    private IntegerProperty columnAmount = new SimpleIntegerProperty();
        public int getColumnAmount() {return columnAmount.get();}
        public IntegerProperty ColumnProperty() {return columnAmount;}
        public void setColumnAmount(int columns) {this.columnAmount.set(columns+1);}


    public LevelFile(String filename, int rows, int columns){
        this.filename.set(filename);
        this.rowAmount.set(rows);
        this.columnAmount.set(columns);
    }
}