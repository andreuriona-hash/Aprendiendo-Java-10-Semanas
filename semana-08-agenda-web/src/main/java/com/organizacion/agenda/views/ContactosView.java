package com.organizacion.agenda.views;

import com.organizacion.agenda.ui.MainLayout;
import com.organizacion.agenda.ui.TarjetaContacto;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "contactos", layout = MainLayout.class)
public class ContactosView extends VerticalLayout {

    public ContactosView() {
        setSizeFull();
        setPadding(true);

        H2 titulo = new H2("Contactos");
        Paragraph descripcion = new Paragraph(
            "Gestiona todos tus contactos en un solo lugar."
        );
        H3 subtitulo = new H3("Mis contactos");

        FlexLayout cuadricula = new FlexLayout();
        cuadricula.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        cuadricula.setWidthFull();

        cuadricula.add(
            new TarjetaContacto("Andre Uriona", "71234567", "andre@email.com"),
            new TarjetaContacto("Monica Fernandez", "78901234", "monica@email.com"),
            new TarjetaContacto("Adolfo Uriona", "78901234", "adolfo@email.com"),
            new TarjetaContacto("Paola Uriona", "69876543", "paola@email.com"),
            new TarjetaContacto("Leonel Uriona", "72345678", "leonel@email.com")
        );

        VerticalLayout contenido = new VerticalLayout(
            titulo, descripcion, subtitulo, cuadricula
        );
        contenido.setPadding(false);

        Div footer = new Div(new Span("Agenda de Contactos v1.0"));
        footer.setWidthFull();

        add(contenido, footer);
        expand(contenido);
    }
}