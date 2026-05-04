package com.organizacion.agenda.views;

import com.organizacion.agenda.modelo.Contacto;
import com.organizacion.agenda.service.ContactoService;
import com.organizacion.agenda.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
    private Grid<Contacto> grid = new Grid<>(Contacto.class, false);
    private Contacto contactoEditando = null;

    public ContactosView(ContactoService servicio) {
        this.servicio = servicio;

        configurarCampos();
        configurarBinder();
        configurarGrid();

        FormLayout formulario = new FormLayout();
        formulario.add(campoNombre, campoEmail, campoTelefono);
        formulario.setColspan(campoNombre, 2);
        formulario.setWidthFull();

        Button btnGuardar = new Button("Guardar", e -> guardar());
        Button btnLimpiar = new Button("Limpiar", e -> limpiar());
        Button btnEliminar = new Button("Eliminar", e -> eliminar());
        btnEliminar.getThemeNames().add("error");

        HorizontalLayout botones = new HorizontalLayout(btnGuardar, btnLimpiar, btnEliminar);

        add(new H2("Contactos"), formulario, botones, grid);
        setWidthFull();
        refrescarGrid();
    }

    private void configurarCampos() {
        campoNombre.setPlaceholder("Ej: Ana Quispe");
        campoEmail.setPlaceholder("Ej: ana@correo.com");
        campoTelefono.setPlaceholder("Ej: 71234567");
        campoNombre.setWidthFull();
        campoEmail.setWidthFull();
        campoTelefono.setWidthFull();
    }

    private void configurarBinder() {
        binder.forField(campoNombre)
                .asRequired("El nombre no puede estar vacío")
                .bind(Contacto::getNombre, Contacto::setNombre);

        binder.forField(campoEmail)
                .bind(Contacto::getEmail, Contacto::setEmail);

        binder.forField(campoTelefono)
                .withConverter(
                        v -> v == null ? "" : String.valueOf(v.intValue()),
                        t -> t == null || t.isEmpty() ? null : Double.valueOf(t))
                .bind(Contacto::getTelefono, Contacto::setTelefono);
    }

    private void configurarGrid() {
        grid.addColumn(Contacto::getNombre).setHeader("Nombre").setSortable(true).setFlexGrow(2);
        grid.addColumn(Contacto::getEmail).setHeader("Correo").setSortable(true).setFlexGrow(2);
        grid.addColumn(Contacto::getTelefono).setHeader("Teléfono").setFlexGrow(1);
        grid.setWidthFull();
        grid.setHeight("260px");

        grid.asSingleSelect().addValueChangeListener(e -> {
            Contacto c = e.getValue();
            if (c != null) {
                binder.readBean(c);
                contactoEditando = c;
            }
        });
    }

    private void guardar() {
        Contacto contacto = contactoEditando != null ? contactoEditando : new Contacto();
        try {
            binder.writeBean(contacto);
            servicio.guardar(contacto);
            Notification.show("Guardado: " + contacto.getNombre());
            limpiar();
        } catch (ValidationException e) {
            // Binder marca los campos con error automáticamente
        }
    }

    private void eliminar() {
        if (contactoEditando == null) {
            Notification.show("Selecciona un contacto del listado");
            return;
        }
        confirmarEliminacion(contactoEditando);
    }

    private void confirmarEliminacion(Contacto contacto) {
        com.vaadin.flow.component.confirmdialog.ConfirmDialog dialogo = 
            new com.vaadin.flow.component.confirmdialog.ConfirmDialog();
        dialogo.setHeader("Eliminar contacto");
        dialogo.setText("Eliminar a " + contacto.getNombre() + "? Esta acción no se puede deshacer.");
        dialogo.setConfirmText("Eliminar");
        dialogo.setConfirmButtonTheme("error primary");
        dialogo.setCancelable(true);
        dialogo.addConfirmListener(e -> {
            servicio.eliminar(contacto);
            refrescarGrid();
            limpiar();
            Notification.show(contacto.getNombre() + " eliminado");
        });
        dialogo.open();
    }

    private void limpiar() {
        binder.readBean(new Contacto());
        contactoEditando = null;
        grid.asSingleSelect().clear();
    }

    private void refrescarGrid() {
        grid.setItems(servicio.obtenerTodos());
    }
}