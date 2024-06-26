package ru.nsu.upprpo.pianogame.model.data.settings;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.nsu.upprpo.pianogame.model.game.ClickType;

import java.nio.file.Path;
import java.util.*;

public class AppSettings implements SettingsProvider {

    private final ClickKeys clickKeys;

    private Path gamePath;

    @JsonCreator
    public AppSettings(
            @JsonProperty("keys") ClickKeys keys,
            @JsonProperty("gamePath") Path gamePath) {
        this.clickKeys = keys;
        this.gamePath = gamePath.toAbsolutePath();
    }

    public static AppSettings getDefault(Path settingsDir) {
        return new AppSettings(new AppSettings.ClickKeys(new Integer[]{0x41, 0x53, 0x44, 0x4A, 0x4B, 0x4C}), settingsDir.resolve("games"));
    }

    @Override
    @JsonIgnore
    public synchronized Map<ClickType, Integer> getGameKeys() {
        return clickKeys.toMap();
    }

    @Override
    public synchronized void setGameKey(ClickType type, int key) {
        clickKeys.set(type, key);
    }

    @Override
    @JsonGetter(value = "gamePath")
    public synchronized Path getGamesDir() {
        return gamePath;
    }

    @Override
    public synchronized void setGamesDir(Path path) {
        this.gamePath = path.toAbsolutePath();
    }

    @JsonGetter(value = "keys")
    public ClickKeys getClickKeys() {
        return clickKeys;
    }

    static class ClickKeys {

        Integer[] values;

        @JsonCreator
        public ClickKeys(@JsonProperty("values") Integer[] values) {
            if (values.length != 6)
                throw new IllegalArgumentException("Wrong values count");
            this.values = Arrays.copyOf(values, 6);
        }

        public void set(ClickType key, int value) {
            switch (key) {
                case LINE_1 -> values[0] = value;
                case LINE_2 -> values[1] = value;
                case LINE_3 -> values[2] = value;
                case LINE_4 -> values[3] = value;
                case LINE_5 -> values[4] = value;
                case LINE_6 -> values[5] = value;
            }
        }

        public Map<ClickType, Integer> toMap() {
            return Map.of(
                    ClickType.LINE_1, values[0],
                    ClickType.LINE_2, values[1],
                    ClickType.LINE_3, values[2],
                    ClickType.LINE_4, values[3],
                    ClickType.LINE_5, values[4],
                    ClickType.LINE_6, values[5]
                    );
        }

        @JsonGetter
        public Integer[] getValues() {
            return values;
        }
    }

}
