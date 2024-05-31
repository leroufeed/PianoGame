package ru.nsu.upprpo.pianogame.control.session.creator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.upprpo.pianogame.control.session.MenuSession;
import ru.nsu.upprpo.pianogame.view.events.PlayerOpenMenuEvent;
import ru.nsu.upprpo.pianogame.view.menu.MenuView;
import ru.nsu.upprpo.pianogame.control.ControllerSessionCreator;
import ru.nsu.upprpo.pianogame.event.EventHandler;

public class MenuSessionCreator extends ControllerSessionCreator {

    private static final Logger logger = LogManager.getLogger(MenuSessionCreator.class);

    private MenuView menuView = null;

    @EventHandler
    public void onMenuOpen(PlayerOpenMenuEvent e) {
        try {
            if (menuView == null) {
                menuView = controller().getScene(MenuView.class);
            }
            if (menuView == null) {
                logger.error("Can't create menu view");
                return;
            }
            controller().setSession(new MenuSession(menuView));
        } catch (Exception ex) {
            logger.fatal("Choice scene create error", ex);
        }
    }

}
