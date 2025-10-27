package ui.components;

import core.AudioManager;
import core.GameManager;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Character;
import model.GameScene;
import ui.Animations;

import java.util.List;

public class MenuScreen {
    public static Scene create(Stage stage, List<GameScene> scenes, List<Character> characters) {
        AudioManager.playMusic("src/assets/sounds/musics/menu/menu.wav", true);

        ImageView bg = new ImageView(new Image("file:src/assets/images/locations/menu/background_menu.jpg"));
        bg.setFitWidth(800);
        bg.setFitHeight(600);

        StackPane root = new StackPane(bg);

        // Crée les menus
        MenuPrincipal menu = new MenuPrincipal();

        root.getChildren().addAll(menu.layout);

        menu.play.setOnAction(_ ->
            Animations.fadeOut(menu.layout, 1, () -> {
                // Initialisation du GameManager avec les scènes et personnages
                GameManager.init(stage, scenes, characters);
            })
        );

        menu.options.setOnAction(_ -> {
            // Retirer le layout menu principal et aller aux options
            root.getChildren().remove(menu.layout);
            // root est ton StackPane qui contient le menu principal
            OptionsScreen os = new OptionsScreen(menu);
            os.create(root);
        });

        menu.quit.setOnAction(_ -> stage.close());

        return new Scene(root, 800, 600);
    }
}
