package ru.nsu.upprpo.pianogame.model.game.level.line;

import ru.nsu.upprpo.pianogame.model.game.level.TileScoreProvider;
import ru.nsu.upprpo.pianogame.model.game.level.line.tile.Tile;

import java.util.Collection;

public interface TileLine extends TileScoreProvider {

    Collection<Tile> getTiles(long startTime, long endTime);

    void getTiles(Collection<Tile> tileCollection, long startTime, long endTime);

    void press(long time);

    void release(long time);

    Tile getTile(long time);

}
