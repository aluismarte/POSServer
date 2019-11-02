package com.alsjava.courses.servers.ui.components;

import com.alsjava.courses.servers.model.interfaces.UIBuilder;
import com.alsjava.courses.servers.utils.Languages;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aluis on 11/2/19.
 */
public class Content extends VerticalLayout implements UIBuilder {

    private final Map<String, TabComponent> tabsToPages = new HashMap<>();
    private final Tabs tabs = new Tabs();
    private final Div divContent = new Div();
    private final ContextMenu contextMenu = new ContextMenu();

    public Content() {
        addClassName("content-panel-app");
        setSizeFull();
        buildLayout();
    }

    @Override
    public void buildLayout() {
        tabs.setWidthFull();
        divContent.setSizeFull();
        add(tabs, divContent);
        tabs.addSelectedChangeListener(event -> {
            removeTabComponent();
            Tab selectedTab = tabs.getSelectedTab();
            if (selectedTab != null) {
                selectedTab.getId().ifPresent(selected -> divContent.add(tabsToPages.get(selected).getComponent()));
            }
        });
        configureContextMenu();
    }

    private void configureContextMenu() {
        contextMenu.setTarget(tabs);
        contextMenu.addItem(Languages.get().i18n("context.menu.close.all.tabs"), event -> removeAllTabs());
    }

    public void addFixedTab(String tabName, Component component, boolean select) {
        addTab(tabName, component, true, select);
    }

    public void addTab(String tabName, Component component) {
        addTab(tabName, component, false, true);
    }

    private void addTab(final String tabName, Component component, boolean fixed, boolean select) {
        TabComponent tabComponent = tabsToPages.get(tabName);
        if (tabComponent == null) {
            tabComponent = new TabComponent(tabName, component, fixed);
            tabsToPages.put(tabName, tabComponent);
            tabs.add(tabComponent.getTab());
        }
        if (select) {
            tabs.setSelectedTab(tabComponent.getTab());
        }
    }

    private void removeAllTabs() {
        List<String> toDelete = new ArrayList<>();
        for (Map.Entry<String, TabComponent> entry : tabsToPages.entrySet()) {
            if (!entry.getValue().isFixed()) {
                toDelete.add(entry.getKey());
            }
        }
        toDelete.forEach(key -> {
            tabs.remove(tabsToPages.get(key).getTab());
            tabsToPages.remove(key);
        });
    }

    private void removeTab(String tabName) {
        TabComponent tabComponent = tabsToPages.get(tabName);
        removeTabComponent();
        tabs.remove(tabComponent.getTab());
        tabsToPages.remove(tabName);
        if (tabsToPages.size() > 0) {
            List<String> keys = new ArrayList<>(tabsToPages.keySet());
            tabs.setSelectedTab(tabsToPages.get(keys.get(keys.size() - 1)).getTab());
        }
    }

    private void removeTabComponent() {
        divContent.removeAll();
    }

    @Override
    public void security() {
    }

    private class TabComponent {

        private Tab tab;
        private Component component;
        private boolean fixed;

        TabComponent(String tabName, Component component, boolean fixed) {
            this.component = component;
            this.fixed = fixed;
            if (this.fixed) {
                this.tab = new Tab(new HorizontalLayout(new Label(tabName)));
            } else {
                Icon icon = VaadinIcon.CLOSE_SMALL.create();
                icon.addClickListener(event -> removeTab(tabName));
                this.tab = new Tab(new HorizontalLayout(new Label(tabName), icon));
            }
            this.tab.setId(tabName);
        }

        Tab getTab() {
            return tab;
        }

        Component getComponent() {
            return component;
        }

        boolean isFixed() {
            return fixed;
        }
    }
}
