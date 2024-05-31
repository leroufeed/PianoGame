package ru.nsu.upprpo.pianogame.model.data.settings;

import ru.nsu.upprpo.pianogame.model.game.ClickType;

import java.nio.file.Path;
import java.util.Map;

public interface SettingsProvider {

    Map<ClickType, Integer> getGameKeys();

    void setGameKey(ClickType type, int key);

    Path getGamesDir();

    void setGamesDir(Path path);

}
