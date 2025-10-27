package core;

import javafx.stage.Stage;
import model.GameScene;
import model.Character;
import ui.components.GameScreen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe qui gère la scène actuelle, les transitions, etc...
 */
public class GameManager {
    private static Stage stage;
    private static final Map<String, GameScene> scenes = new HashMap<>();
    private static final Map<String, Character> characters = new HashMap<>();
    private static String currentSceneId;

    // Initialisation avec les scènes et personnages
    public static void init(Stage s, List<GameScene> loadedScenes, List<Character> loadedCharacters) {
        stage = s;

        // Charger les scènes
        for (GameScene scene : loadedScenes) {
            scenes.put(scene.getId(), scene);
        }

        // Charger les personnages
        for (Character character : loadedCharacters) {
            characters.put(character.getId(), character);
        }

        currentSceneId = loadedScenes.getFirst().getId(); // première scène
        showScene(currentSceneId);
    }

    // Afficher une scène donnée
    public static void showScene(String id) {
        currentSceneId = id;
        GameScene scene = scenes.get(id);
        stage.setScene(GameScreen.create(stage, scene, characters));
    }

    // Passer à la scène suivante
    public static void nextScene(String id) {
        showScene(id);
    }
}
