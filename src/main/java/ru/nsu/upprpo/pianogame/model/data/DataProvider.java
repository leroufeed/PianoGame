package ru.nsu.upprpo.pianogame.model.data;

import ru.nsu.upprpo.pianogame.model.data.game.GameProvider;
import ru.nsu.upprpo.pianogame.model.data.score.ScoreProvider;
import ru.nsu.upprpo.pianogame.model.data.settings.SettingsProvider;

public interface DataProvider {

    GameProvider gameProvider();

    ScoreProvider scoreProvider();

    SettingsProvider settingProvider();

}
