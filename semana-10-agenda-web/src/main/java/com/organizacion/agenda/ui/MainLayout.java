package com.organizacion.agenda.ui;

import com.organizacion.agenda.views.ContactosView;
import com.organizacion.agenda.views.EventosView;
import com.organizacion.agenda.views.InicioView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout() {
        H2 titulo = new H2("Agenda de Contactos");

        MenuBar menu = new MenuBar();
        menu.addItem(new RouterLink("Inicio", InicioView.class));
        menu.addItem(new RouterLink("Contactos", ContactosView.class));
        menu.addItem(new RouterLink("Eventos", EventosView.class));

        HorizontalLayout barra = new HorizontalLayout(titulo, menu);
        barra.setWidthFull();
        barra.expand(titulo);
        barra.setAlignItems(FlexComponent.Alignment.CENTER);

        addToNavbar(barra);
    }
}