package tools.files;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import model.fileData.LevelFile;

import java.io.*;

public class FileUtils {
    public static ListProperty<LevelFile> discoverFiles(){
        ListProperty<LevelFile> levelFiles = new SimpleListProperty<>();
        levelFiles.setValue(FXCollections.observableArrayList());

        File levelDirectory  = new File("./out/production/pacmanfx/Cartes/");
        if(!levelDirectory.isDirectory())
            return null;

        File[] subdirectories = levelDirectory.listFiles();

        for(File subdir : subdirectories) {
            if (subdir.isDirectory()) {
                LevelFile lf = getLevelFileFromDirectory(subdir);
                if(lf != null)
                    levelFiles.add(lf);
            }
        }
        return levelFiles;
    }

    private static LevelFile getLevelFileFromDirectory(File directory){
        File[] localFiles = directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return (name.endsWith(".collisionMap") || name.endsWith(".entityMap"));
            }
        });
        int maxColumns = 0;
        int maxRows = 0;
        String filename = "";
        for(File f : localFiles){
            filename = trimExtension(f.getName());
            int lines = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(f.getAbsoluteFile()))) {
                String str = null;
                while ((str = reader.readLine()) != null){
                    lines++;
                    if(str.length() > maxColumns)
                        maxColumns = str.length();
                }
                if(lines > maxRows)
                    maxRows = lines;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LevelFile lf = new LevelFile(filename, maxRows, maxColumns);
        return lf;
    }

    private static String trimExtension(String fileName){
        String toReturn = fileName.substring(0, fileName.lastIndexOf('.'));
        return toReturn;
    }
}
