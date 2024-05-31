package ru.nsu.upprpo.pianogame.model;

import ru.nsu.upprpo.pianogame.model.data.game.GameDescription;
import ru.nsu.upprpo.pianogame.model.data.score.GameScore;
import ru.nsu.upprpo.pianogame.model.data.settings.SettingsProvider;
import ru.nsu.upprpo.pianogame.model.exception.ModelException;

import java.util.Collection;

public interface ModelInterface {

    void startGame(GameDescription gameDescription) throws ModelException;

    Collection<GameDescription> getGameDescriptions();

    Collection<GameScore> getScores();

    SettingsProvider getSettings();

}
