package com.alsjava.courses.servers.ui;

import com.alsjava.courses.servers.POSServerUI;
import com.alsjava.courses.servers.model.LoginSession;
import com.alsjava.courses.servers.model.interfaces.UIBuilder;
import com.alsjava.courses.servers.ui.components.Content;
import com.alsjava.courses.servers.ui.components.MenuPanel;
import com.alsjava.courses.servers.ui.fixed.DashboardTab;
import com.alsjava.courses.servers.utils.Constants;
import com.alsjava.courses.servers.utils.Languages;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.server.VaadinSession;

/**
 * Created by aluis on 11/2/19.
 */
public class App extends AppLayout implements UIBuilder {

    private Content content;
    private MenuPanel menuPanel;

    public App() {
        if (VaadinSession.getCurrent().getAttribute(LoginSession.class) == null) {
            return;
        }
        content = new Content();
        menuPanel = new MenuPanel(content);
        buildLayout();
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        getUI().ifPresent(ui -> {
            LoginSession loginSession = ui.getSession().getAttribute(LoginSession.class);
            if (loginSession == null) {
                ui.navigate(POSServerUI.class);
            }
        });
    }

    @Override
    public void buildLayout() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, new DrawerToggle(), new Label(Constants.get().APP_NAME));
        addToDrawer(menuPanel);
        setContent(content);
        content.addFixedTab(Languages.get().i18n("fixed.tab.dashboard"), new DashboardTab(), true);
    }

    @Override
    public void security() {
    }
}
