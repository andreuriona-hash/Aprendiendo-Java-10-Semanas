package com.organizacion.agenda.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("contactos")
public class ContactosView extends VerticalLayout {

    public ContactosView() {
        setPadding(true);
        setSpacing(true);

        H1 titulo = new H1("Contactos");
        Paragraph mensaje = new Paragraph(
            "Aquí aparecerán los contactos. Próxima semana."
        );

        Button volver = new Button("Volver al inicio");
        volver.addClickListener(e -> 
            getUI().ifPresent(ui -> ui.navigate(""))
        );

        Notification.show("Vista de contactos cargada");

        add(titulo, mensaje, volver);
    }
}