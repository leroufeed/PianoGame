package ru.nsu.upprpo.pianogame.control.session.creator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.upprpo.pianogame.control.session.GameChoiceSession;
import ru.nsu.upprpo.pianogame.view.choice.ChoiceView;
import ru.nsu.upprpo.pianogame.view.events.PlayerOpenGameChooseEvent;
import ru.nsu.upprpo.pianogame.control.ControllerSessionCreator;
import ru.nsu.upprpo.pianogame.event.EventHandler;

public class GameChoiceSessionCreator extends ControllerSessionCreator {

    private static final Logger logger = LogManager.getLogger(GameChoiceSessionCreator.class);

    private ChoiceView choiceView = null;

    @EventHandler
    public void onGameChoiceOpen(PlayerOpenGameChooseEvent e) {
        try {
            if (choiceView == null) {
                choiceView = controller().getScene(ChoiceView.class);
            }
            if (choiceView == null) {
                logger.error("Can't create game choice view");
                return;
            }
            GameChoiceSession session = new GameChoiceSession(choiceView);
            controller().setSession(session);
        } catch (Exception ex) {
            logger.fatal("Choice scene create error", ex);
        }
    }

}
