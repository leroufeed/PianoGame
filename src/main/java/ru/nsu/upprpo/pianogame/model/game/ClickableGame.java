package ru.nsu.upprpo.pianogame.model.game;

public interface ClickableGame {

    void press(ClickType type, long globalTime);

    void release(ClickType type, long globalTime);

    ClickStatus getLastStatus(ClickType type, long globalTime);

}
