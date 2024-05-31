package ru.nsu.upprpo.pianogame.control.session.creator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.upprpo.pianogame.control.session.ScoreSession;
import ru.nsu.upprpo.pianogame.view.events.PlayerOpenScoreEvent;
import ru.nsu.upprpo.pianogame.view.score.ScoreView;
import ru.nsu.upprpo.pianogame.control.ControllerSessionCreator;
import ru.nsu.upprpo.pianogame.event.EventHandler;

public class ScoreSessionCreator extends ControllerSessionCreator {

    private static final Logger logger = LogManager.getLogger(ScoreSessionCreator.class);

    private ScoreView choiceView;

    @EventHandler
    public void onScoreOpen(PlayerOpenScoreEvent e) {
        try {
            if (choiceView == null) {
                choiceView = controller().getScene(ScoreView.class);
            }
            if (choiceView == null) {
                logger.error("Can't create choice view");
                return;
            }
            ScoreSession scoreSession = new ScoreSession(choiceView);
            controller().setSession(scoreSession);
            scoreSession.updateScores();
        } catch (Exception ex) {
            logger.error("Score scene create error", ex);
        }
    }

}
