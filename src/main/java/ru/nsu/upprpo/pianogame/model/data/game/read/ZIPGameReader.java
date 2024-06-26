package ru.nsu.upprpo.pianogame.model.data.game.read;

import ru.nsu.upprpo.pianogame.model.exception.IncorrectGameFileException;
import ru.nsu.upprpo.pianogame.model.exception.ModelException;
import ru.nsu.upprpo.pianogame.model.exception.ModelIOException;
import ru.nsu.upprpo.pianogame.model.game.level.PianoGame;
import ru.nsu.upprpo.pianogame.model.game.level.line.tile.Tile;

import java.util.HashMap;
import java.util.Optional;

public class ZIPGameReader extends ZIPReader {

    public ZIPGameReader() {
        super(new HashMap<>());
        readerStructure.put("gameLore.json", ReaderMethods::readGameLore);
        readerStructure.put("tiles1.json", ReaderMethods::readTiles);
        readerStructure.put("tiles2.json", ReaderMethods::readTiles);
        readerStructure.put("tiles3.json", ReaderMethods::readTiles);
        readerStructure.put("tiles4.json", ReaderMethods::readTiles);
        readerStructure.put("tiles5.json", ReaderMethods::readTiles);
        readerStructure.put("tiles6.json", ReaderMethods::readTiles);
    }

    public PianoGame readGame(RawDataContainer data) throws ModelException {

        GameLore lore = readLore(data);

        Tile[][] gameTiles = readTiles(data);

        try {
            return new PianoGame(lore.name(), lore.levelTime(), ReaderMethods.convert(gameTiles));
        } catch (IllegalArgumentException e) {
            throw new IncorrectGameFileException("Create game " + lore.name() + " error", e);
        }
    }

    private Tile[][] readTiles(RawDataContainer data) throws ModelException {
        Tile[][] gameTiles = new Tile[6][];
        for (int i = 1; i <= 6; i++) {
            Optional<Tile[]> tiles = data.getData(Tile[].class, "tiles"+i+".json");
            if (tiles.isEmpty())
                throw new ModelIOException("Can't read tiles " + i + " from file, see other errors");
            gameTiles[i-1] = tiles.get();
        }
        return gameTiles;
    }

    private GameLore readLore(RawDataContainer data) throws ModelException {
        Optional<GameLore> lore = data.getData(GameLore.class, "gameLore.json");
        if (lore.isEmpty())
            throw new ModelIOException("Can't read game lore from file, see other errors");
        return lore.get();
    }

}
