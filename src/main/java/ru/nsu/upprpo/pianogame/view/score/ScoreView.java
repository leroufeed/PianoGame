package ru.nsu.upprpo.pianogame.view.score;

import ru.nsu.upprpo.pianogame.view.AppScene;

import java.util.Collection;

public abstract class ScoreView extends AppScene {

    public abstract void setGameScores(Collection<ScoreUnit> choices);

}
