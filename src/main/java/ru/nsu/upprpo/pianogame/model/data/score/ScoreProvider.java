package ru.nsu.upprpo.pianogame.model.data.score;

import ru.nsu.upprpo.pianogame.model.data.game.GameDescription;

import java.util.Collection;

public interface ScoreProvider {

    public Collection<GameScore> getScores();

    public void addScore(GameDescription description, long score);

}
