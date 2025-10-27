package ui;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animations {

    public static void fadeIn(Node node, double durationSec) {
        FadeTransition ft = new FadeTransition(Duration.seconds(durationSec), node);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    public static void fadeOut(Node node, double durationSec, Runnable after) {
        FadeTransition ft = new FadeTransition(Duration.seconds(durationSec), node);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setOnFinished(_ -> after.run());
        ft.play();
    }
}
