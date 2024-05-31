package ru.nsu.upprpo.pianogame.control.exception;

import ru.nsu.upprpo.pianogame.control.ControllerModule;

public class ModuleLiveCycleException extends ControllerException {

    public ModuleLiveCycleException(String message, ControllerModule module) {
        super(module + " : " + message);
    }

}
