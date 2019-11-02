package com.alsjava.courses.servers.ui.components;

import com.alsjava.courses.servers.model.interfaces.UIBuilder;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * Created by aluis on 11/2/19.
 */
public class MenuPanel extends VerticalLayout implements UIBuilder {

    private Content content;

    public MenuPanel(Content content) {
        this.content = content;
        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        buildLayout();
        security();
    }

    @Override
    public void buildLayout() {
    }

    @Override
    public void security() {
    }
}
