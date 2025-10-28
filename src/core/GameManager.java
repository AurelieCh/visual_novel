package core;

import javafx.stage.Stage;
import model.*;
import model.Character;
import ui.components.GameScreen;
import java.util.*;

/**
 * Gère la logique du jeu : chargement des scènes, navigation entre les nœuds, etc.
 */
public class GameManager {

    private static Stage stage;
    private static final Map<String, GameScene> scenes = new HashMap<>();
    private static final Map<String, Character> characters = new HashMap<>();

    private static String currentSceneId;
    private static String currentNodeId;

    /**
     * Initialise le jeu avec les scènes et personnages chargés
     */
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

        // Démarrer avec la première scène et son premier nœud
        if (!loadedScenes.isEmpty()) {
            currentSceneId = loadedScenes.getFirst().getId();
            GameScene firstScene = loadedScenes.getFirst();
            currentNodeId = firstScene.getStartNode();
            showNode(currentSceneId, currentNodeId);
        } else {
            System.err.println("[ERREUR] Aucune scène chargée !");
        }
    }

    /**
     * Affiche un nœud spécifique dans une scène
     */
    public static void showNode(String sceneId, String nodeId) {
        GameScene scene = scenes.get(sceneId);
        if (scene == null) {
            System.err.println("[ERREUR] Scène introuvable : " + sceneId);
            return;
        }

        SceneNode node = scene.getNodes().get(nodeId);
        if (node == null) {
            System.err.println("[ERREUR] Nœud introuvable : " + nodeId + " dans la scène " + sceneId);
            return;
        }

        currentSceneId = sceneId;
        currentNodeId = nodeId;

        stage.setScene(GameScreen.create(stage, scene, characters, currentNodeId));
    }

    /**
     * Passe au nœud suivant (via un choix par exemple)
     */
    public static void nextNode(String nextNodeId) {
        showNode(currentSceneId, nextNodeId);
    }
}
