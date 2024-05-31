package ru.nsu.upprpo.pianogame.model.game.level;

import ru.nsu.upprpo.pianogame.model.game.level.line.tile.TileStatus;

import java.util.Collection;

public interface TileScoreProvider {

    Collection<TileStatus> getTileStatistic();

    void getTileStatistic(Collection<TileStatus> statuses);

    long getMissCount();

    long getScore();

}
