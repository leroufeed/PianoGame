package ru.nsu.upprpo.pianogame.model.exception;

import ru.nsu.upprpo.pianogame.model.data.game.GameDescription;

public class IncorrectGameFileException extends ModelException {


    public IncorrectGameFileException(GameDescription description, String message) {
        super(message + "\n" + description);
    }
    public IncorrectGameFileException(GameDescription description, String message, Throwable throwable) {
        super(message + "\n" + description, throwable);
    }

    public IncorrectGameFileException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
