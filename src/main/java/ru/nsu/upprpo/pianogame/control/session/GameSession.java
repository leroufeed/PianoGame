package ru.nsu.upprpo.pianogame.control.session;

import ru.nsu.upprpo.pianogame.model.game.ClickType;
import ru.nsu.upprpo.pianogame.view.game.*;
import ru.nsu.upprpo.pianogame.control.Controller;
import ru.nsu.upprpo.pianogame.control.ControllerSession;
import ru.nsu.upprpo.pianogame.control.exception.ControllerException;
import ru.nsu.upprpo.pianogame.control.exception.GameAlreadyStartedException;
import ru.nsu.upprpo.pianogame.event.EventHandler;
import ru.nsu.upprpo.pianogame.model.game.level.TileLinesGame;
import ru.nsu.upprpo.pianogame.model.game.level.line.tile.Tile;
import ru.nsu.upprpo.pianogame.model.game.level.line.tile.TileStatus;
import ru.nsu.upprpo.pianogame.view.AppScene;
import ru.nsu.upprpo.pianogame.view.events.PlayerKeyEvent;
import ru.nsu.upprpo.pianogame.view.events.game.PlayerGameStopEvent;
import ru.nsu.upprpo.pianogame.view.events.game.PlayerPauseEvent;
import ru.nsu.upprpo.pianogame.view.events.game.PlayerResumeEvent;

import java.util.*;

public class GameSession extends ControllerSession {

    private final TileLinesGame game;

    private Map<Integer, ClickType> keyMap;

    private final GameView gameView;

    private final Object graphicLock = new Object();

    private final Timer timer = new Timer();

    private TimerTask graphicTask = null;


    public GameSession(TileLinesGame game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
    }

    @Override
    public void start(Controller controller) throws ControllerException {
        super.start(controller);
        gameView.setTimeAdapter(new SimpleTimeProvider(game));
        keyMap = new HashMap<>();
        for (Map.Entry<ClickType, Integer> e : controller().model().getSettings().getGameKeys().entrySet()) {
            keyMap.put(e.getValue(), e.getKey());
        }
        if(!game.start())
            throw new GameAlreadyStartedException("I tried to start " + game.name() + " game, but it was already started");
        graphicStart();
    }

    @Override
    public void stop() throws ControllerException {
        super.stop();
        gameEnd();
    }

    @Override
    public AppScene scene() {
        return gameView;
    }

    public void gameEnd() {
        if (game.stop()) {
            gameView.stop();
            timer.cancel();
            showScore();
        }
    }

    public void gamePause() {
        if (game.pause()) {
            graphicPause();
        }
    }

    public void gameResume() {
        if (game.resume()) {
            graphicStart();
        }
    }

    private void showScore() {
        Collection<TileStatus> statistic = game.getTileStatistic();
        long score = game.getScore();
        long clicked = statistic.stream().filter(TileStatus::clicked).count();
        long missed = game.getMissCount();
        gameView.showScore(String.format("""
                Score: %d
                Clicked: %d
                Missed: %d
                Miss clicked: %d
                """, score, clicked, statistic.size() - clicked, missed));
    }

    private void graphicPause() {
        gameView.stop();
        synchronized (graphicLock) {
            if (graphicTask != null) {
                graphicTask.cancel();
                graphicTask = null;
            }
        }
    }

    private void graphicStart() {
        gameView.start();
        synchronized (graphicLock) {
            if (graphicTask != null) {
                graphicTask.cancel();
            }
            graphicTask = new GraphicUpdaterTask(game.timeToShow() / 4_000_000);
            timer.schedule(graphicTask, 0, 20);
        }
    }

    @EventHandler
    public void onGameExit(PlayerGameStopEvent e) {
        gameEnd();
    }

    @EventHandler
    public void onPause(PlayerPauseEvent e) {
        gamePause();
    }

    @EventHandler
    public void onResume(PlayerResumeEvent e) {
        gameResume();
    }

    @EventHandler
    public void onGameKey(PlayerKeyEvent e) {
        if (e.isPress()) {
            game.press(keyMap.get(e.keyCode().getCode()), game.toLocalTime(e.createNanoTine()));
        } else {
            game.release(keyMap.get(e.keyCode().getCode()), game.toLocalTime(e.createNanoTine()));
        }
    }

    private class GraphicUpdaterTask extends TimerTask {

        private final long fullUpdateTime;

        private long lastFullUpdate;

        public GraphicUpdaterTask(long fullUpdateTime) {
            lastFullUpdate = System.nanoTime() - fullUpdateTime;
            this.fullUpdateTime = fullUpdateTime;
        }

        @Override
        public void run() {
            long nTime = System.nanoTime();
            if (game.isOutOfTime(nTime)) {
                gameEnd();
            }
            if (System.nanoTime() - lastFullUpdate > fullUpdateTime) {
                fullUpdate(nTime);
                lastFullUpdate = System.nanoTime();
            }
            long time = game.toLocalTime(nTime);
            ClickType[] types = game.availableClickTypes();
            LineStatus[] status = new LineStatus[types.length];
            for (int i = 0; i < types.length; i++) {
                status[i] = switch (game.getLastStatus(types[i], time)) {
                    case GOOD_CLICKED -> LineStatus.GOOD_CLICK;
                    case GOOD_PRESSED -> LineStatus.GOOD_HOLD;
                    case MISSED_TILE -> LineStatus.NO_CLICK;
                    case NOTHING -> LineStatus.NOTHING;
                };
            }
            gameView.updateStatus(status);
        }


        public void fullUpdate(long nTime) {
            long time = game.toLocalTime(nTime);
            long endTime = time + game.timeToShow() * 2;
            ClickType[] types = game.availableClickTypes();
            DrawnInfo[][] tiles = new DrawnInfo[types.length][];
            for (int i = 0; i < types.length; i++)
                tiles[i] = game.getLine(types[i]).getTiles(time, endTime).stream().map(InfoAdapter::new).toArray(InfoAdapter[]::new);
            gameView.updateView(tiles);
        }

    }

    private record InfoAdapter(Tile tile) implements DrawnInfo {

        @Override
        public long endTime() {
            return tile.endTime();
        }

        @Override
        public long startTime() {
            return tile.startTime();
        }

        @Override
        public DrawnTileType type() {
            return switch (tile.type()) {
                case LONG -> DrawnTileType.LONG;
                case DEFAULT -> DrawnTileType.SHORT;
            };
        }

        @Override
        public boolean isClicked() {
            return tile.getStatus().clicked();
        }
    }

    private record SimpleTimeProvider(TileLinesGame game) implements TimeProvider {

        @Override
        public long getRelativeFromNano(long timeNS) {
            return game.toLocalTime(timeNS);
        }

        @Override
        public long getTimeToShow() {
            return game.timeToShow();
        }

    }

}
