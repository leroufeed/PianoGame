package ru.nsu.upprpo.pianogame.view.score;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import ru.nsu.upprpo.pianogame.event.EventManager;
import ru.nsu.upprpo.pianogame.view.events.PlayerOpenMenuEvent;
import ru.nsu.upprpo.pianogame.view.score.component.ScoreBox;

import java.util.Collection;

public class ScoreAppScene extends ScoreView {

    public VBox scrollBox;

    public void setGameScores(Collection<ScoreUnit> scores) {
        Platform.runLater(() -> scrollBox.getChildren().setAll(scores.stream().map(ScoreBox::new).toList()));
    }

    @FXML
    public void pressMenu() {
        EventManager.runEvent(new PlayerOpenMenuEvent());
    }

}
