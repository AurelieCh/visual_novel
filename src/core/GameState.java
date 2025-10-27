package core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe permettant de sauvegarder l'état actuel du jeu (chapitre, inventaire, etc.)
 */
public class GameState implements Serializable {

    private String currentChapter;
    private String currentScene;
    private Map<String, Object> variables = new HashMap<>();

    public GameState(String chapter, String scene) {
        this.currentChapter = chapter;
        this.currentScene = scene;
    }

    public String getCurrentChapter() { return currentChapter; }
    public String getCurrentScene() { return currentScene; }

    public void setCurrentScene(String sceneId) {
        this.currentScene = sceneId;
    }

    public void setVariable(String key, Object value) {
        variables.put(key, value);
    }

    public Object getVariable(String key) {
        return variables.get(key);
    }

    public boolean hasVariable(String key) {
        return variables.containsKey(key);
    }

    @Override
    public String toString() {
        return "GameState{chapter='" + currentChapter + "', scene='" + currentScene + "', vars=" + variables + "}";
    }
}

