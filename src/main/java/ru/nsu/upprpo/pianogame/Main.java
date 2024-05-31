package ru.nsu.upprpo.pianogame;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.nsu.upprpo.pianogame.view.events.PlayerOpenMenuEvent;
import ru.nsu.upprpo.pianogame.control.Controller;
import ru.nsu.upprpo.pianogame.control.session.creator.GameChoiceSessionCreator;
import ru.nsu.upprpo.pianogame.control.session.creator.MenuSessionCreator;
import ru.nsu.upprpo.pianogame.control.session.creator.ScoreSessionCreator;
import ru.nsu.upprpo.pianogame.event.EventManager;
import ru.nsu.upprpo.pianogame.model.RootModel;
import ru.nsu.upprpo.pianogame.view.View;

import java.nio.file.Path;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        View view = new View(primaryStage);
        RootModel model = RootModel.createModel(Path.of("."));
        Controller controller = new Controller(view, model);
        controller.addSessionCreator(new MenuSessionCreator());
        controller.addSessionCreator(new ScoreSessionCreator());
        controller.addSessionCreator(new GameChoiceSessionCreator());
        EventManager.runEvent(new PlayerOpenMenuEvent());
    }

}