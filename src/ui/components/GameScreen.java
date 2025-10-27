package ui.components;

import core.GameManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Choice;
import model.GameScene;
import model.Character;
import ui.UIStyles;

import java.util.Map;

public class GameScreen {

    public static Scene create(Stage stage, GameScene sceneData, Map<String, Character> charactersMap) {
        // Infos
        System.out.println(stage.toString());

        // ==== Background ====
        ImageView bg = new ImageView(new Image("file:src/assets/images/locations/cottage/" + sceneData.getLocation() + ".jpg"));
        bg.setFitWidth(800);
        bg.setFitHeight(600);

        // ==== Character portrait ====
        Character currentCharacter = charactersMap.get(sceneData.getCharacter());
        ImageView portrait = null;
        if (currentCharacter != null) {
            String mood = sceneData.getMood() != null ? sceneData.getMood() : "default";
            portrait = new ImageView(new Image("file:src/assets/images/characters/" + currentCharacter.getId() + "/"
                    + currentCharacter.getId() + "_" + mood + ".jpg"));
            portrait.setFitHeight(400);
            portrait.setPreserveRatio(true);
        }

        // ==== Dialogue box ====
        DialogueBox dialogueBox = new DialogueBox();

        // ==== Choices ====
        VBox choicesBox = new VBox(10);
        UIStyles.centerVBox(choicesBox);
        choicesBox.setDisable(true);

        for (Choice c : sceneData.getChoices()) {
            Button btn = new Button(c.getText());
            UIStyles.styleChoiceButton(btn);
            btn.setOnAction(_ -> GameManager.nextScene(c.getNext()));
            btn.setVisible(false);
            choicesBox.getChildren().add(btn);
        }

        // ==== Layout interface ====
        VBox dialogueArea = new VBox(10, dialogueBox, choicesBox);
        UIStyles.bottomCenterVBox(dialogueArea);
        dialogueArea.setTranslateY(-20);

        StackPane root = new StackPane(bg);
        if (portrait != null)
            root.getChildren().add(portrait);
        root.getChildren().add(dialogueArea);
        StackPane.setAlignment(dialogueArea, Pos.BOTTOM_CENTER);

        Scene scene = new Scene(root, 800, 600);

        // ==== Afficher dialogue ====
        dialogueBox.showDialogue(currentCharacter, sceneData.getText(), () -> enableChoices(choicesBox));

        // === Activer les choix après animation typewriter ===
        // On attend la fin de l’effet typewriter si c’est celui utilisé
        if (currentCharacter != null && "typewriter".equals(currentCharacter.getAnimationType())) {
            int delay = sceneData.getText().length() * 30 + 300; // durée typewriter + marge
            new javafx.animation.PauseTransition(javafx.util.Duration.millis(delay))
                    .setOnFinished(_ -> enableChoices(choicesBox));
        } else {
            enableChoices(choicesBox);
        }

        return scene;
    }

    private static void enableChoices(VBox choicesBox) {
        choicesBox.setDisable(false);
        for (javafx.scene.Node node : choicesBox.getChildren()) {
            node.setVisible(true);
        }
    }
}
