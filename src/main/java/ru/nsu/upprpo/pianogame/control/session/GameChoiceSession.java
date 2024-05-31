package ru.nsu.upprpo.pianogame.control.session;

import ru.nsu.upprpo.pianogame.view.choice.ChoiceView;
import ru.nsu.upprpo.pianogame.control.Controller;
import ru.nsu.upprpo.pianogame.control.ControllerSession;
import ru.nsu.upprpo.pianogame.control.exception.ControllerException;
import ru.nsu.upprpo.pianogame.control.session.creator.GameSessionCreator;
import ru.nsu.upprpo.pianogame.event.Event;
import ru.nsu.upprpo.pianogame.event.GameChoiceEvent;
import ru.nsu.upprpo.pianogame.model.data.game.GameDescription;
import ru.nsu.upprpo.pianogame.view.AppScene;
import ru.nsu.upprpo.pianogame.view.choice.GameChoiceUnit;

import java.util.Objects;
import java.util.stream.Collectors;

public class GameChoiceSession extends ControllerSession {

    private final ChoiceView choiceView;

    private GameSessionCreator gameSessionCreator;

    public GameChoiceSession(ChoiceView choiceView) {
        this.choiceView = choiceView;

    }

    @Override
    public void start(Controller controller) throws ControllerException {
        super.start(controller);
        updateDescriptions();
        this.gameSessionCreator = new GameSessionCreator(this);
        controller().addSessionCreator(gameSessionCreator);
    }

    @Override
    public void stop() throws ControllerException {
        super.stop();
        controller().removeSessionCreator(gameSessionCreator);
    }

    public void updateDescriptions() {
        choiceView.setGameChoice(
                controller().model().getGameDescriptions()
                        .stream()
                        .filter(Objects::nonNull)
                        .map(ViewChoiceUint::new)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public AppScene scene() {
        return choiceView;
    }

    private record ViewChoiceUint(GameDescription description) implements GameChoiceUnit {

        @Override
            public String getText() {
                return description.name();
            }

            @Override
            public Event gameStartEvent() {
                return new GameChoiceEvent(description);
            }

    }

}
