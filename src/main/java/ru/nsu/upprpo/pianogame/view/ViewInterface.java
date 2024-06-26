package ru.nsu.upprpo.pianogame.view;

import java.io.IOException;

public interface ViewInterface {

    void setScene(AppScene scene);

    <T extends AppScene> T getScene(Class<T> clazz) throws IOException;

    void close();

}
