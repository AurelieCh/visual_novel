package model;

import java.util.HashMap;
import java.util.Map;

public class GameScene {
    private final String id;
    private final String location; // image de fond
    private final String music;    // musique associée
    private final String startNode; // id du dialogue de départ
    private final Map<String, SceneNode> nodes = new HashMap<>();

    public GameScene(String id, String location, String music, String startNode) {
        this.id = id;
        this.location = location;
        this.music = music;
        this.startNode = startNode;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getStartNode() {
        return startNode;
    }

    public Map<String, SceneNode> getNodes() {
        return nodes;
    }

    /**
     * Retourne le nœud de départ
     */
    public SceneNode getStartNodeObject() {
        return nodes.get(startNode);
    }

    @Override
    public String toString() {
        return "GameScene{" +
                "id='" + id + '\'' +
                ", location='" + location + '\'' +
                ", nodes=" + nodes.size() +
                '}';
    }
}
