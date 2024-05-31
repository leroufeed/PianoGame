package ru.nsu.upprpo.pianogame.view.game;

public interface GameRender {

    void updateView(DrawnInfo[][] nTiles, LineStatus[] status);

    void setTimeAdapter(TimeProvider adapter);

}
