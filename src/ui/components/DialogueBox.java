package ui.components;

import javafx.animation.*;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import model.Character;

public class DialogueBox extends VBox {
    private final Label nameLabel;
    private final Label textLabel;

    public DialogueBox() {
        nameLabel = new Label();
        textLabel = new Label();
        textLabel.setWrapText(true);

        this.setSpacing(5);
        this.getChildren().addAll(nameLabel, textLabel);
        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); -fx-padding: 20; -fx-background-radius: 15;");
    }

    public void showDialogue(Character character, String text, Runnable onFinished) {
        if (character != null) {
            nameLabel.setText(character.getDisplayName());
            nameLabel.setTextFill(Color.web(character.getTextColor()));

            FontWeight weight = character.isBold() ? FontWeight.BOLD : FontWeight.NORMAL;
            FontPosture posture = character.isItalic() ? FontPosture.ITALIC : FontPosture.REGULAR;

            textLabel.setFont(Font.font(character.getFontFamily(), weight, posture, 18));
            textLabel.setTextFill(Color.web(character.getTextColor()));
            textLabel.setText(text);

            applyAnimation(character.getAnimationType(), onFinished);
        } else {
            nameLabel.setText("");
            textLabel.setText(text);
            if (onFinished != null) onFinished.run();
        }
    }

    private void applyAnimation(String type, Runnable onFinished) {
        textLabel.setOpacity(1);
        textLabel.setTranslateX(0);

        switch (type) {
            case "fade":
                FadeTransition fade = new FadeTransition(Duration.millis(600), textLabel);
                fade.setFromValue(0);
                fade.setToValue(1);
                fade.setOnFinished(_ -> { if(onFinished != null) onFinished.run(); });
                fade.play();
                break;

            case "shake":
                TranslateTransition shake = new TranslateTransition(Duration.millis(80), textLabel);
                shake.setFromX(-5);
                shake.setToX(5);
                shake.setCycleCount(6);
                shake.setAutoReverse(true);
                shake.setOnFinished(_ -> { if(onFinished != null) onFinished.run(); });
                shake.play();
                break;

            case "typewriter":
                typewriterEffect(textLabel, onFinished);
                break;

            case "bounce":
                ScaleTransition bounce = new ScaleTransition(Duration.millis(400), textLabel);
                bounce.setFromY(0.8);
                bounce.setToY(1);
                bounce.setCycleCount(1);
                bounce.setOnFinished(_ -> { if(onFinished != null) onFinished.run(); });
                bounce.play();
                break;

            default:
                if(onFinished != null) onFinished.run();
                break;
        }
    }

    private void typewriterEffect(Label label, Runnable onFinished) {
        String fullText = label.getText();
        label.setText("");
        Timeline timeline = new Timeline();

        for (int i = 0; i < fullText.length(); i++) {
            final int index = i;
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(30 * i),
                    _ -> label.setText(fullText.substring(0, index + 1))));
        }
        if(onFinished != null) timeline.setOnFinished(_ -> onFinished.run());
        timeline.play();
    }
}
