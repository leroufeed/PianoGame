package ru.nsu.upprpo.pianogame.model.data.game.read;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import ru.nsu.upprpo.pianogame.model.game.ClickType;
import ru.nsu.upprpo.pianogame.model.game.level.line.tile.Tile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ReaderMethods {

    private static final ObjectReader jsonReader = new ObjectMapper().reader();

    private ReaderMethods() {}

    public static Tile[] readTiles(InputStream is) throws IOException {
        return jsonReader.readValue(is, Tile[].class);
    }

    public static GameLore readGameLore(InputStream is) throws IOException {
        return jsonReader.readValue(is, GameLore.class);
    }

    public static Map<ClickType, Tile[]> convert(Tile[][] tiles) {
        Map<ClickType, Tile[]> map = new HashMap<>();
        for (int i = 0; i < 6; i++) {
            map.put(ClickType.values()[i], tiles[i]);
        }
        return map;
    }

}
