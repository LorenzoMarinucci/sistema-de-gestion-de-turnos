package totem.vistas;

import java.awt.event.ActionListener;

public interface Vista {

    void cerrarVista();
    void abrirVista();
    void setActionListener(ActionListener actionListener);

}
