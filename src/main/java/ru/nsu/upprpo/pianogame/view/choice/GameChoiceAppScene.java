package ru.nsu.upprpo.pianogame.view.choice;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import ru.nsu.upprpo.pianogame.event.EventManager;
import ru.nsu.upprpo.pianogame.view.choice.component.ChoicePane;
import ru.nsu.upprpo.pianogame.view.events.PlayerOpenMenuEvent;

import java.util.Collection;

public class GameChoiceAppScene extends ChoiceView {

    public VBox scrollBox;

    @Override
    public void setGameChoice(Collection<GameChoiceUnit> choices) {
        Platform.runLater(() -> {
                    scrollBox.getChildren().clear();
                    scrollBox.getChildren().addAll(choices.stream().map(ChoicePane::new).toList());
                }
        );
    }


    @FXML
    public void pressMenu() {
        EventManager.runEvent(new PlayerOpenMenuEvent());
    }

}
