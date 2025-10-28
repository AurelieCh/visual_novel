package core;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import model.Character;
import ui.components.GameScreen;

import java.util.*;

/**
 * Gère la logique du jeu : chargement des scènes, navigation entre les nœuds, et gestion du Stage.
 */
public class GameManager {

    private static Stage stage;
    private static final Map<String, GameScene> scenes = new HashMap<>();
    private static final Map<String, Character> characters = new HashMap<>();

    private static String currentSceneId;
    private static String currentNodeId;

    /**
     * Initialise le jeu avec les scènes et personnages chargés et démarre la transition.
     * Appelé depuis l'écran de menu (MenuScreen).
     */
    public static void init(Stage s, List<GameScene> loadedScenes, List<Character> loadedCharacters,
                            double initialWidth, double initialHeight, boolean initialMaximized) {
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

            // 🚨 Appel de la version avec paramètres pour la transition Menu -> Jeu
            showNode(currentSceneId, currentNodeId, initialWidth, initialHeight, initialMaximized);
        } else {
            System.err.println("[ERREUR] Aucune scène chargée !");
        }
    }

    /**
     * [VERSION 1/2 : TRANSITION MENU -> JEU]
     * Affiche un nœud spécifique en utilisant les dimensions sauvegardées du menu.
     */
    public static void showNode(String sceneId, String nodeId,
                                double initialWidth, double initialHeight, boolean initialMaximized) {

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

        // Créer la nouvelle scène de jeu
        Scene newScene = GameScreen.create(stage, scene, characters, currentNodeId);

        // Affecter la nouvelle scène
        stage.setScene(newScene);

        // 🚨 Restauration de la taille du Stage avec les valeurs passées (du menu)
        if (initialMaximized) {
            stage.setMaximized(true);
        } else {
            stage.setWidth(initialWidth);
            stage.setHeight(initialHeight);
        }
    }

    /**
     * [VERSION 2/2 : NAVIGATION INTER-NŒUDS]
     * Affiche un nœud spécifique en utilisant la taille actuelle du Stage.
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

        // 🚨 Sauvegarde des dimensions ACTUELLES du Stage
        double currentWidth = stage.getWidth();
        double currentHeight = stage.getHeight();
        boolean isMaximized = stage.isMaximized();

        // Créer la nouvelle scène de jeu
        Scene newScene = GameScreen.create(stage, scene, characters, currentNodeId);

        // Affecter la nouvelle scène
        stage.setScene(newScene);

        // 🚨 Restauration de la taille du Stage avec les valeurs actuelles
        if (isMaximized) {
            stage.setMaximized(true);
        } else {
            stage.setWidth(currentWidth);
            stage.setHeight(currentHeight);
        }
    }

    /**
     * Passe au nœud suivant (via un choix par exemple).
     */
    public static void nextNode(String nextNodeId) {
        // 🚨 Appel de la version sans paramètres (navigation DANS le jeu)
        showNode(currentSceneId, nextNodeId);
    }
}