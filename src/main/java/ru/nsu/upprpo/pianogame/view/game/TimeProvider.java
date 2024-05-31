package ru.nsu.upprpo.pianogame.view.game;

public interface TimeProvider {

    long getRelativeFromNano(long timeNS);

    long getTimeToShow();

}
