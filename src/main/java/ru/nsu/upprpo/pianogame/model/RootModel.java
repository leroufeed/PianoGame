package ru.nsu.upprpo.pianogame.model;

import ru.nsu.upprpo.pianogame.event.EventManager;
import ru.nsu.upprpo.pianogame.model.data.DataController;
import ru.nsu.upprpo.pianogame.model.data.DataProvider;
import ru.nsu.upprpo.pianogame.model.data.game.GameDescription;
import ru.nsu.upprpo.pianogame.model.data.game.read.RawDataContainer;
import ru.nsu.upprpo.pianogame.model.data.game.read.ZIPGameReader;
import ru.nsu.upprpo.pianogame.model.data.score.GameScore;
import ru.nsu.upprpo.pianogame.model.data.settings.SettingsProvider;
import ru.nsu.upprpo.pianogame.model.event.ModelStartTileLinesGameEvent;
import ru.nsu.upprpo.pianogame.model.exception.IncorrectGameFileException;
import ru.nsu.upprpo.pianogame.model.exception.ModelException;
import ru.nsu.upprpo.pianogame.model.exception.ModelIOException;
import ru.nsu.upprpo.pianogame.model.game.level.PianoGame;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Collection;

public class RootModel implements ModelInterface {

    private final DataProvider dataProvider;

    private final ZIPGameReader gameReader = new ZIPGameReader();

    private RootModel(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public static RootModel createModel(Path dir) {
        return new RootModel(DataController.createController(dir));
    }

    @Override
    public void startGame(GameDescription gameDescription) throws ModelException {
        try {
            RawDataContainer dataContainer = gameReader.readZIP(gameDescription.gameContainer());

            if (!dataContainer.getHash().equals(gameDescription.hash())) {
                throw new IncorrectGameFileException(
                        gameDescription,
                        String.format("Wrong hash, expect %s value %s", gameDescription.hash(), dataContainer.getHash())
                );
            }

            PianoGame game = gameReader.readGame(dataContainer);

            game.onEnd(() -> dataProvider.scoreProvider().addScore(gameDescription, game.getScore()));

            EventManager.runEvent(new ModelStartTileLinesGameEvent(dataContainer, game));
        } catch (NoSuchFileException e) {
            throw new IncorrectGameFileException( gameDescription, "The file has been deleted", e);
        } catch (IOException e) {
            throw new ModelIOException(e);
        }
    }

    @Override
    public Collection<GameDescription> getGameDescriptions() {
        return dataProvider.gameProvider().readGameDescriptions();
    }

    @Override
    public Collection<GameScore> getScores() {
        return dataProvider.scoreProvider().getScores();
    }

    @Override
    public SettingsProvider getSettings() {
        return dataProvider.settingProvider();
    }
}
