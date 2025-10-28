package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Représente un choix de dialogue menant vers un autre nœud.
 */
public class Choice {
    private final String text;
    private final String next; // id du nœud suivant
    private final Map<String, String> effects = new HashMap<>();

    public Choice(String text, String next) {
        this.text = text;
        this.next = next;
    }

    public String getText() {
        return text;
    }

    public String getNext() {
        return next;
    }
}
