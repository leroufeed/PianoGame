package ru.nsu.upprpo.pianogame.view.factories;

import ru.nsu.upprpo.pianogame.view.choice.GameChoiceAppScene;
import ru.nsu.upprpo.pianogame.view.SceneFactory;

import java.net.URL;

public class GameChoiceSceneFactory extends SceneFactory<GameChoiceAppScene> {

    @Override
    protected URL getFXMLPath() {
        return getClass().getResource("/fxml/ChoiceScene.fxml");
    }

    @Override
    protected Object controllerFXMLFactory(Class<?> clazz) {
        return new GameChoiceAppScene();
    }
}
