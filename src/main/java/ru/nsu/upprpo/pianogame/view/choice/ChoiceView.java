package ru.nsu.upprpo.pianogame.view.choice;

import ru.nsu.upprpo.pianogame.view.AppScene;

import java.util.Collection;

public abstract class ChoiceView extends AppScene {

    public abstract void setGameChoice(Collection<GameChoiceUnit> choices);

}
