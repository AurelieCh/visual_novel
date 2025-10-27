package model;

public class Character {
    private String id;
    private String displayName;
    private String portraitPath;
    private String textColor;
    private String fontFamily;
    private boolean bold;
    private boolean italic;
    private String animationType; // "shake", "fade", "bounce", etc.

    public Character(String id, String displayName, String portraitPath, String textColor,
                     String fontFamily, boolean bold, boolean italic, String animationType) {
        this.id = id;
        this.displayName = displayName;
        this.portraitPath = portraitPath;
        this.textColor = textColor;
        this.fontFamily = fontFamily;
        this.bold = bold;
        this.italic = italic;
        this.animationType = animationType;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getTextColor() {
        return textColor;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public boolean isBold() {
        return bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public String getAnimationType() {
        return animationType;
    }
}
