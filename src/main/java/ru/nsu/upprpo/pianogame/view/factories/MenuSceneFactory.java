package ru.nsu.upprpo.pianogame.view.factories;

import ru.nsu.upprpo.pianogame.view.menu.MenuAppScene;
import ru.nsu.upprpo.pianogame.view.SceneFactory;

import java.net.URL;


public class MenuSceneFactory extends SceneFactory<MenuAppScene> {

    @Override
    protected URL getFXMLPath() {
        return getClass().getResource("/fxml/MainScene.fxml");
    }

    @Override
    protected Object controllerFXMLFactory(Class<?> clazz) {
        return new MenuAppScene();
    }
}
