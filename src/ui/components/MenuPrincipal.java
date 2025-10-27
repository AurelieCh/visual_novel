package ui.components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ui.UIStyles;

public class MenuPrincipal {

    public VBox layout;
    public Button play;
    public Button options;
    public Button quit;
    public Label title;

    public MenuPrincipal() {
        layout = new VBox(20);
        layout.setStyle("-fx-alignment: center;");

        title = new Label("🌙 Aventure Pulpeuse");
        UIStyles.styleTitleLabel(title);

        play = new Button("▶ Commencer");
        options = new Button("⚙ Options");
        quit = new Button("❌ Quitter");

        UIStyles.styleButton(play);
        UIStyles.styleButton(options);
        UIStyles.styleButton(quit);

        layout.getChildren().addAll(title, play, options, quit);
    }
}
