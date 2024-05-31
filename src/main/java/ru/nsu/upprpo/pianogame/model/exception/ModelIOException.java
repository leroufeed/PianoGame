package ru.nsu.upprpo.pianogame.model.exception;

import java.io.IOException;

public class ModelIOException extends ModelException {

    public ModelIOException(IOException ex) {
        super(ex);
    }

    public ModelIOException(String reason) {
        super(reason);
    }

    @Override
    public String getMessage() {
        return "IOException in model";
    }
}
