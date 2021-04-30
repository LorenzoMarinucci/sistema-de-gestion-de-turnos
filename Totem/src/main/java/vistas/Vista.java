package vistas;

import java.awt.event.ActionListener;

public interface Vista extends ActionListener {

    void cerrarVista();
    void abrirVista(ActionListener actionListener);

}
