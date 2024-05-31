package ru.nsu.upprpo.pianogame.view.choice.component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import ru.nsu.upprpo.pianogame.view.choice.GameChoiceUnit;
import ru.nsu.upprpo.pianogame.event.EventManager;

public class ChoicePane extends HBox {

    public ChoicePane(GameChoiceUnit gameChoiceUnit) {
        Label textLabel = new Label(gameChoiceUnit.getText());
        Button button = new Button("Start");
        button.setOnMouseClicked(e -> EventManager.runEvent(gameChoiceUnit.gameStartEvent()));
        getChildren().add(textLabel);
        getChildren().add(button);
    }

}
