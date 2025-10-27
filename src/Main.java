import core.SceneLoader;
import core.CharacterLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import model.GameScene;
import model.Character;
import ui.components.MenuScreen;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        List<GameScene> scenes = SceneLoader.loadChapter("src/assets/story/chapter_1.json");
        List<Character> characters = CharacterLoader.loadCharacters("src/assets/characters/characters.json");

        stage.setTitle("🌙 Aventure Pulpeuse");
        stage.setScene(MenuScreen.create(stage, scenes, characters));
        stage.show();
    }
}
