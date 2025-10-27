package core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.GameScene;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Classe permettant de lire les fichiers JSON (scénario)
 */
public class SceneLoader {

    public static List<GameScene> loadChapter(String path) {
        try (FileReader reader = new FileReader(path)) {
            Gson gson = new Gson();
            Type type = new TypeToken<StoryWrapper>() {}.getType();
            StoryWrapper data = gson.fromJson(reader, type);
            return data.scenes;
        } catch (Exception e) {
            System.err.println("[SceneLoader] Erreur lors de la récupération des scènes : " + e.getMessage());
            return null;
        }
    }

    private static class StoryWrapper {
        List<GameScene> scenes;
    }
}

