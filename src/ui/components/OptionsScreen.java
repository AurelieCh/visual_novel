package ui.components;

import core.AudioManager;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import ui.Animations;
import ui.UIStyles;

public class OptionsScreen {

    private final MenuPrincipal menu;

    public OptionsScreen(MenuPrincipal menu) {
        this.menu = menu;
    }

    protected StackPane create(StackPane root) {
        // --- Fond du menu
        ImageView bg = new ImageView(new Image("file:src/assets/images/menu/locations/background_menu.jpg"));
        bg.setFitWidth(800);
        bg.setFitHeight(600);

        // --- Layout principal
        VBox layout = new VBox(25);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 30; -fx-background-radius: 15;");

        // --- Titre
        Label title = new Label("⚙ Options Audio");
        UIStyles.styleTitleLabel(title);

        // --- Groupes audio
        VBox musicGroup = createAudioSlider("Musique", AudioManager.getMusicVolume(), AudioManager::setMusicVolume);
        VBox sfxGroup   = createAudioSlider("Bruitages", AudioManager.getSfxVolume(), AudioManager::setSfxVolume);
        VBox voiceGroup = createAudioSlider("Voix", AudioManager.getVoiceVolume(), AudioManager::setVoiceVolume);

        // Bouton Retour
        Button back = new Button("⬅ Retour");
        UIStyles.styleButton(back); back.setOnAction(_ -> {
            // Retirer le layout Options et revenir au menu principal
            root.getChildren().remove(layout);
            Animations.fadeIn(root, 0.5);
            // Ajouter le menu principal au root
            root.getChildren().add(this.menu.layout); });

        // --- Assemblage
        layout.getChildren().addAll(title, musicGroup, sfxGroup, voiceGroup, back);
        StackPane.setAlignment(layout, Pos.CENTER);

        // --- Ajout et animation
        root.getChildren().add(layout);
        Animations.fadeIn(layout, 0.5);

        return root;
    }


    private static VBox createAudioSlider(String labelText, double initialValue, java.util.function.DoubleConsumer onValueChange) {
        Label label = new Label(labelText);
        UIStyles.styleLabel(label);

        Slider slider = new Slider(0, 1, initialValue);
        slider.setBlockIncrement(0.05);
        UIStyles.styleSlider(slider);
        slider.valueProperty().addListener((_, _, newV) -> onValueChange.accept(newV.doubleValue()));

        VBox group = new VBox(5, label, slider);
        group.setAlignment(Pos.CENTER);
        return group;
    }
}
