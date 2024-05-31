package ru.nsu.upprpo.pianogame.model.event;

import ru.nsu.upprpo.pianogame.model.game.level.TileLinesGame;
import ru.nsu.upprpo.pianogame.model.data.game.read.RawDataContainer;

public class ModelStartTileLinesGameEvent extends ModelStartGameEvent {

    private final TileLinesGame game;

    public ModelStartTileLinesGameEvent(RawDataContainer rawDataContainer, TileLinesGame game) {
        super(rawDataContainer);
        this.game = game;
    }

    public TileLinesGame getGame() {
        return game;
    }
}
