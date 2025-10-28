package model;

import core.SceneCharacter;

import java.util.List;

public class SceneNode {
    private String id;
    private String text;
    private List<SceneCharacter> characters;
    private String speakingCharacterId;
    private List<Choice> choices;

    public String getText() {
        return text;
    }

    public List<SceneCharacter> getCharacters() {
        return characters;
    }

    public String getSpeakingCharacterId() {
        return speakingCharacterId;
    }

    public List<Choice> getChoices() {
        return choices;
    }
}
