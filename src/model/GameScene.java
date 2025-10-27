package model;

import java.util.List;

/**
 * Classe permettant de stocker les infos d’une scène
 */
public class GameScene {
    public String id;
    public String location;
    public String character;
    public String mood;
    public String text;
    public List<Choice> choices;

    public GameScene(String id, String location, String character, String text, List<Choice> choices) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getText() {
        return text;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public String getMood() {
        return mood;
    }
}
