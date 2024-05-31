package ru.nsu.upprpo.pianogame.control.session;

import ru.nsu.upprpo.pianogame.view.score.ScoreUnit;
import ru.nsu.upprpo.pianogame.view.score.ScoreView;
import ru.nsu.upprpo.pianogame.control.ControllerSession;
import ru.nsu.upprpo.pianogame.model.data.score.GameScore;
import ru.nsu.upprpo.pianogame.view.AppScene;

import java.util.stream.Collectors;

public class ScoreSession extends ControllerSession {

    private final ScoreView choiceView;

    public ScoreSession(ScoreView choiceView) {
        this.choiceView = choiceView;
    }

    public void updateScores() {
        choiceView.setGameScores(
                controller().model().getScores()
                        .stream()
                        .map(ViewScore::new)
                        .collect(Collectors.toList()));
    }

    @Override
    public AppScene scene() {
        return choiceView;
    }

    private record ViewScore(GameScore gameScore) implements ScoreUnit {

        @Override
        public String name() {
            return gameScore.name();
        }

        @Override
        public long score() {
            return gameScore.maxScore();
        }
    }

}
