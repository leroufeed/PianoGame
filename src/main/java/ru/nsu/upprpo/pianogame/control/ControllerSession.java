package ru.nsu.upprpo.pianogame.control;

import ru.nsu.upprpo.pianogame.view.AppScene;


/**
 * Controller session module. Manages a separate scene.
 * **/
public abstract class ControllerSession extends ControllerModule {

    public abstract AppScene scene();

}
