package com.organizacion.agenda.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class InicioView extends VerticalLayout {

    public InicioView() {
        setPadding(true);
        setSpacing(true);

        H1 titulo = new H1("Agenda de Contactos");
        H2 subtitulo = new H2("Tu agenda personal");
        Paragraph descripcion = new Paragraph(
            "Guarda, edita y busca tus contactos en un solo lugar."
        );

        Button verContactos = new Button("Ver contactos");
        verContactos.addClickListener(e -> 
            getUI().ifPresent(ui -> ui.navigate("contactos"))
        );

        add(titulo, subtitulo, descripcion, verContactos);
    }
}