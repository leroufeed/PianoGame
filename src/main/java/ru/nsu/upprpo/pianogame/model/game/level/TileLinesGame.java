package ru.nsu.upprpo.pianogame.model.game.level;

import ru.nsu.upprpo.pianogame.model.game.ClickType;
import ru.nsu.upprpo.pianogame.model.game.ClickableGame;
import ru.nsu.upprpo.pianogame.model.game.TimerGame;
import ru.nsu.upprpo.pianogame.model.game.level.line.TileLine;

public interface TileLinesGame extends TimerGame, ClickableGame, TileScoreProvider {

    String name();

    TileLine getLine(ClickType clickType);

    ClickType[] availableClickTypes();

    /**
     * @return nano time
     * **/
    long timeToShow();



}
