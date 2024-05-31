package ru.nsu.upprpo.pianogame.view.menu;

import javafx.fxml.FXML;
import ru.nsu.upprpo.pianogame.event.EventManager;
import ru.nsu.upprpo.pianogame.view.events.PlayerOpenGameChooseEvent;
import ru.nsu.upprpo.pianogame.view.events.PlayerOpenScoreEvent;
import ru.nsu.upprpo.pianogame.view.events.menu.PlayerGameExitEvent;

public class MenuAppScene extends MenuView {

    @FXML
    public void startAction() {
        EventManager.runEvent(new PlayerOpenGameChooseEvent());
    }

    @FXML
    public void scoreAction() {
        EventManager.runEvent(new PlayerOpenScoreEvent());
    }

    @FXML
    public void exitAction() {
        EventManager.runEvent(new PlayerGameExitEvent());
    }
}
