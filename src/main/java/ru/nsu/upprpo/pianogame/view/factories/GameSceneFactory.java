package ru.nsu.upprpo.pianogame.view.factories;

import ru.nsu.upprpo.pianogame.view.game.GameAppScene;
import ru.nsu.upprpo.pianogame.view.SceneFactory;

import java.net.URL;

public class GameSceneFactory extends SceneFactory<GameAppScene> {

    @Override
    protected URL getFXMLPath() {
        return getClass().getResource("/fxml/GameScene.fxml");
    }

    @Override
    protected Object controllerFXMLFactory(Class<?> clazz) {
        return new GameAppScene();
    }
}
