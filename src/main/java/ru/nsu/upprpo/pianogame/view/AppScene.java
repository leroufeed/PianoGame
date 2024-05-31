package ru.nsu.upprpo.pianogame.view;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import ru.nsu.upprpo.pianogame.view.events.PlayerKeyEvent;
import ru.nsu.upprpo.pianogame.event.EventManager;

public abstract class AppScene {

    @FXML
    private Parent root;

    public Parent content() {
        return root;
    }

        @FXML
        public void onKeyPress(KeyEvent keyEvent) {
            EventManager.runEvent(new PlayerKeyEvent(true, keyEvent.getCode()));
        }

        @FXML
        public void onKeyRelease(KeyEvent keyEvent) {
        }

}
