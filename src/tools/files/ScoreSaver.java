package tools.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class ScoreSaver {
    public static void saveScore(String levelName, String playerName, int points){
        String toWrite = "\n"+playerName+";"+points;
        try {
            Files.writeString(Paths.get("./out/production/pacmanfx/Cartes/"+levelName+"/"+levelName+".score"),toWrite + System.lineSeparator(),CREATE, APPEND);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ArchivedScore> GetScoresForMap(String levelname){
        File f = new File("./out/production/pacmanfx/Cartes/"+levelname+"/"+levelname+".score");
        ArrayList<ArchivedScore> scores = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(f.getAbsoluteFile()))) {
            String str = null;
            while ((str = reader.readLine()) != null){
                if(!str.contains(";"))
                    break;
                String[] parts = str.split(";");
                ArchivedScore toAdd = new ArchivedScore(parts[0],parts[1]);
                scores.add(toAdd);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }
}
