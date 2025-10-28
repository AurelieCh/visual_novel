package core;

public class SceneCharacter {
    private String id;
    private Object x;
    private String mood;

    /**
     * Retourne la position X en pixels
     */
    public double getPositionX() {
        if (x instanceof Number) return ((Number) x).doubleValue();
        if (x instanceof String alias) {
            return switch (alias.toLowerCase()) {
                case "left" -> -200;
                case "center" -> 0;
                case "right" -> 200;
                default -> 0; // fallback
            };
        }
        return 0;
    }

    public String getMood() {
        return mood;
    }

    public String getId() {
        return id;
    }
}
