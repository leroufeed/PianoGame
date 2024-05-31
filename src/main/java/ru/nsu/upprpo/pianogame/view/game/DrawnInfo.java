package ru.nsu.upprpo.pianogame.view.game;

public interface DrawnInfo {

    long endTime();

    long startTime();

    DrawnTileType type();

    boolean isClicked();

}
