package ui;

import core.AudioManager;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Slider;

public class UIStyles {

    public static final String TEXT_COLOR = "#000000";
    public static final Font MAIN_FONT = Font.font("Arial", 18);

    // ======== Bouttons =========== //
    public static void styleButton(Button btn) {
        btn.setFont(MAIN_FONT);
        btn.setTextFill(Color.web(TEXT_COLOR));
        btn.setStyle("""
                    -fx-background-color: rgba(255,255,255,0.1);
                    -fx-border-color: black;
                    -fx-border-radius: 15;
                    -fx-background-radius: 15;
                    -fx-border-width: 2;
                    -fx-padding: 10 25 10 25;
                """);

        btn.setOnMouseEntered(_ -> {
            btn.setStyle("""
                    -fx-background-color: rgba(255,255,255,0.3);
                    -fx-border-color: #D3D3D3;
                    -fx-border-radius: 15;
                    -fx-background-radius: 15;
                    -fx-border-width: 2;
                    -fx-padding: 10 25 10 25;
                    """);

            AudioManager.playSfx("src/assets/sounds/others/mouse_clic.wav");
        });

        btn.setOnMouseExited(_ -> btn.setStyle("""
                    -fx-background-color: rgba(255,255,255,0.1);
                    -fx-border-color: black;
                    -fx-border-radius: 15;
                    -fx-background-radius: 15;
                    -fx-border-width: 2;
                    -fx-padding: 10 25 10 25;
                """));
    }

    // === Boutons de choix (dans les dialogues) ===
    public static void styleChoiceButton(Button button) {
        button.setStyle("""
                    -fx-background-color: rgba(255,255,255,0.2);
                    -fx-text-fill: white;
                    -fx-font-size: 14px;
                    -fx-border-color: white;
                    -fx-border-radius: 10;
                    -fx-background-radius: 10;
                    -fx-padding: 5 15 5 15;
                """);
        button.setOnMouseEntered(_ -> button.setStyle("""
                    -fx-background-color: rgba(255,255,255,0.4);
                    -fx-text-fill: white;
                    -fx-font-size: 14px;
                    -fx-border-color: white;
                    -fx-border-radius: 10;
                    -fx-background-radius: 10;
                    -fx-padding: 5 15 5 15;
                """));
        button.setOnMouseExited(_ -> styleChoiceButton(button));
    }

    // ======= Labels ======= //
    public static void styleLabel(Label lbl) {
        lbl.setFont(MAIN_FONT);
        lbl.setTextFill(Color.web(TEXT_COLOR));
    }

    // ======= Labels titre ======= //
    public static void styleTitleLabel(Label lbl) {
        lbl.setFont(Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 32)); // gros et gras
        lbl.setTextFill(Color.web(TEXT_COLOR));
    }

    // ===== Sliders stylés =====
    public static void styleSlider(Slider slider) {
        // Taille plus fine et centrage
        slider.setPrefWidth(180);
        slider.setMaxWidth(180);
        slider.setMinWidth(180);

        slider.setStyle("""
                    -fx-control-inner-background: rgba(255,255,255,0.2);
                    -fx-background-color: transparent;
                    -fx-border-color: transparent;
                    -fx-border-radius: 10;
                    -fx-padding: 5;
                    -fx-focus-color: transparent;
                    -fx-faint-focus-color: transparent;
                """);

        // Custom CSS pour la "track" et le "thumb"
        slider.lookupAll(".track").forEach(track ->
                track.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-background-radius: 10;")
        );
        slider.lookupAll(".thumb").forEach(thumb ->
                thumb.setStyle("""
                            -fx-background-color: white;
                            -fx-background-radius: 50%;
                            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 6, 0, 0, 2);
                        """)
        );

        // Hover effect dynamique (un peu de glow)
        slider.setOnMouseEntered(_ ->
                slider.lookupAll(".thumb").forEach(thumb ->
                        thumb.setStyle("""
                                    -fx-background-color: #00ccff;
                                    -fx-background-radius: 50%;
                                    -fx-effect: dropshadow(gaussian, rgba(0,204,255,0.8), 8, 0, 0, 0);
                                """))
        );

        slider.setOnMouseExited(_ ->
            slider.lookupAll(".thumb").forEach(thumb ->
                    thumb.setStyle("""
                                -fx-background-color: white;
                                -fx-background-radius: 50%;
                                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 6, 0, 0, 2);
                            """)
            ));
    }

    // === Outils d’alignement ===
    public static void centerVBox(javafx.scene.layout.VBox box) {
        box.setAlignment(Pos.CENTER);
    }

    public static void bottomCenterVBox(javafx.scene.layout.VBox box) {
        box.setAlignment(Pos.BOTTOM_CENTER);
    }
}
