package ru.nsu.upprpo.pianogame.model.data.game;

import java.util.Collection;

public interface GameProvider {

    Collection<GameDescription> readGameDescriptions();

}
