package ru.nsu.upprpo.pianogame.model.event;

import ru.nsu.upprpo.pianogame.event.Event;
import ru.nsu.upprpo.pianogame.model.data.game.read.RawDataContainer;

public class ModelStartGameEvent extends Event {

    private final RawDataContainer container;

    public ModelStartGameEvent(RawDataContainer source) {
        super(true);
        this.container = source;
    }

    public RawDataContainer getData() {
        return container;
    }

}
