package ru.nsu.upprpo.pianogame.view.factories;

import ru.nsu.upprpo.pianogame.view.score.ScoreAppScene;
import ru.nsu.upprpo.pianogame.view.SceneFactory;

import java.net.URL;

public class ScoreSceneFactory extends SceneFactory<ScoreAppScene> {

    @Override
    protected URL getFXMLPath() {
        return getClass().getResource("/fxml/ScoreScene.fxml");
    }

    @Override
    protected Object controllerFXMLFactory(Class<?> clazz) {
        return new ScoreAppScene();
    }
}
