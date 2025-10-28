package ui.components;


import core.GameManager;
import core.SceneCharacter;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Character;
import model.Choice;
import model.GameScene;
import model.SceneNode;
import ui.UIStyles;

import java.util.Map;

public class GameScreen {
    public static Scene create(Stage stage, GameScene sceneData, Map<String, Character> charactersMap, String nodeId) {
        // ==== Background ====
        ImageView bg = new ImageView(new Image("file:src/assets/images/locations/cottage/" + sceneData.getLocation() + ".jpg"));

        // ==== Layout principal ====
        StackPane root = new StackPane();

        bg.fitWidthProperty().bind(root.widthProperty());
        bg.fitHeightProperty().bind(root.heightProperty());
        bg.setPreserveRatio(false); // Mettre à 'false' si on veut que l'image remplisse l'espace

        root.getChildren().add(bg);
        SceneNode node = sceneData.getNodes().get(nodeId);

        // ==== Personnages à l'écran ====
        for (SceneCharacter sc : node.getCharacters()) {
            Character charData = charactersMap.get(sc.getId());
            if (charData != null) {
                ImageView portrait = new ImageView(new Image(
                        "file:src/assets/images/characters/" + charData.getId() + "/" +
                                charData.getId() + "_" + sc.getMood() + ".jpg"
                ));

                UIStyles.setPosCharacter(portrait, sc, stage.heightProperty());
                root.getChildren().add(portrait);
            }
        }

        // ==== Dialogue Box ====
        DialogueBox dialogueBox = new DialogueBox();
        UIStyles.setPosDialogBox(dialogueBox);
        root.getChildren().add(dialogueBox);

        // ==== Choices ====
        FlowPane choicesBox = new FlowPane(10, 10);
        UIStyles.setPosChoices(choicesBox);
        choicesBox.setDisable(true); // désactivé tant que le texte n'est pas fini

        for (Choice c : node.getChoices()) {
            Button btn = new Button(c.getText());
            UIStyles.styleChoiceButton(btn);
            btn.setVisible(false);
            btn.setOnAction(_ -> GameManager.nextNode(c.getNext()));
            choicesBox.getChildren().add(btn);
        }
        root.getChildren().add(choicesBox);

        // ==== Affichage du dialogue ====
        Character speakingCharacter = charactersMap.get(node.getSpeakingCharacterId());
        showDialogueWithCallback(dialogueBox, speakingCharacter, node.getText(), choicesBox);
        return new Scene(root, 800, 600);
    }


    /**
     * Affiche le texte avec typewriter ou autre animation et active les choix ensuite
     */
    private static void showDialogueWithCallback(DialogueBox box, Character character, String text, FlowPane choicesBox) {
        box.showDialogue(character, text, () ->
                enableChoices(choicesBox)
        );
    }


    /**
     * Rend les boutons cliquables et visibles
     */
    private static void enableChoices(FlowPane choicesBox) {
        choicesBox.setDisable(false);
        for (javafx.scene.Node node : choicesBox.getChildren()) {
            node.setVisible(true);
        }
    }
}