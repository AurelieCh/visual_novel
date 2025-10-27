package model;

/**
 * Classe illustrant un choix possible dans une scène
 */
public class Choice {
    public String text;
    public String next;

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

