package tools.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class ScoreSaver {
    public static void saveScore(String levelName, String playerName, int points){
        String toWrite = playerName+";"+points;
        try {
            Files.writeString(Paths.get("./out/production/pacmanfx/Cartes/"+levelName+"/"+levelName+".score"),toWrite + System.lineSeparator(),CREATE, APPEND);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
