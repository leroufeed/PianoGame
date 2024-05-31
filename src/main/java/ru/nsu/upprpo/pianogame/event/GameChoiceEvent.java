package ru.nsu.upprpo.pianogame.event;

import ru.nsu.upprpo.pianogame.model.data.game.GameDescription;

public class GameChoiceEvent extends Event {

    private final GameDescription gameDescription;

    public GameChoiceEvent(GameDescription gameDescription) {
        super(true);
        this.gameDescription = gameDescription;
    }

    public GameDescription getGameDescription() {
        return gameDescription;
    }

}
