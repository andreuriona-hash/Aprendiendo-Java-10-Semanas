package com.organizacion.agenda.views;

import com.organizacion.agenda.modelo.Contacto;
import com.organizacion.agenda.service.ContactoService;
import com.organizacion.agenda.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;

@Route(value = "contactos", layout = MainLayout.class)
public class ContactosView extends VerticalLayout {

    private final ContactoService servicio;

    private TextField campoNombre = new TextField("Nombre completo");
    private EmailField campoEmail = new EmailField("Correo electrónico");
    private NumberField campoTelefono = new NumberField("Teléfono");

    private Binder<Contacto> binder = new Binder<>(Contacto.class);

    public ContactosView(ContactoService servicio) {
        this.servicio = servicio;

        campoNombre.setPlaceholder("Ej: Ana Quispe");
        campoEmail.setPlaceholder("Ej: ana@correo.com");
        campoTelefono.setPlaceholder("Ej: 71234567");
        campoTelefono.setWidthFull();

        FormLayout formulario = new FormLayout();
        formulario.add(campoNombre, campoEmail, campoTelefono);
        formulario.setColspan(campoNombre, 2);

        binder.forField(campoNombre)
                .asRequired("El nombre no puede estar vacío")
                .bind(Contacto::getNombre, Contacto::setNombre);

        binder.forField(campoEmail)
                .bind(Contacto::getEmail, Contacto::setEmail);

        binder.forField(campoTelefono)
                .bind(c -> c.getTelefono() != null ? Double.valueOf(c.getTelefono()) : null,
                      (c, val) -> c.setTelefono(val != null ? String.valueOf(val.intValue()) : ""));

        Button btnGuardar = new Button("Guardar contacto");
        Button btnLimpiar = new Button("Limpiar");

        btnGuardar.addClickListener(e -> {
            Contacto contacto = new Contacto();
            try {
                binder.writeBean(contacto);
                servicio.guardar(contacto);
                Notification.show("Contacto guardado: " + contacto.getNombre());
                limpiarFormulario();
            } catch (ValidationException ex) {
                // Binder ya muestra los errores en los campos
            }
        });

        btnLimpiar.addClickListener(e -> limpiarFormulario());

        add(formulario, btnGuardar, btnLimpiar);
    }

    private void limpiarFormulario() {
        binder.readBean(new Contacto());
    }
}