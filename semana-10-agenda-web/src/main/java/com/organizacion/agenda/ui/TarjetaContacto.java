package com.organizacion.agenda.ui;

import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class TarjetaContacto extends Div {

    public TarjetaContacto(String nombre, String telefono, String email) {
        Avatar avatar = new Avatar(nombre);
        avatar.setWidth("48px");
        avatar.setHeight("48px");

        H3 nombreLabel = new H3(nombre);

        HorizontalLayout filaTel = crearFila(VaadinIcon.PHONE, telefono);
        HorizontalLayout filaEmail = crearFila(VaadinIcon.ENVELOPE, email);

        VerticalLayout datos = new VerticalLayout(nombreLabel, filaTel, filaEmail);
        datos.setPadding(false);
        datos.setSpacing(false);

        HorizontalLayout contenido = new HorizontalLayout(avatar, datos);
        contenido.setAlignItems(com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment.CENTER);

        add(contenido);
    }

    private HorizontalLayout crearFila(VaadinIcon tipo, String texto) {
        Icon icono = new Icon(tipo);
        icono.setSize("14px");

        HorizontalLayout fila = new HorizontalLayout(icono, new Span(texto));
        fila.setAlignItems(com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment.CENTER);
        return fila;
    }
}