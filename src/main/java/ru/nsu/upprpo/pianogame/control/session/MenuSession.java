package ru.nsu.upprpo.pianogame.control.session;

import ru.nsu.upprpo.pianogame.view.menu.MenuView;
import ru.nsu.upprpo.pianogame.control.ControllerSession;
import ru.nsu.upprpo.pianogame.event.EventHandler;
import ru.nsu.upprpo.pianogame.view.AppScene;
import ru.nsu.upprpo.pianogame.view.events.menu.PlayerGameExitEvent;

public class MenuSession extends ControllerSession {

    private final MenuView menuView;

    public MenuSession(MenuView menuView) {
        this.menuView = menuView;
    }

    @Override
    public AppScene scene() {
        return menuView;
    }

    @EventHandler
    public void onExit(PlayerGameExitEvent e) {
        controller().end();
    }


}
