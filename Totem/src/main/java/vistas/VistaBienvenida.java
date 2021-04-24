package vistas;

import java.awt.event.ActionListener;

public interface VistaBienvenida extends ActionListener{

	void setActionListener(ActionListener actionListener);
	void cerrarVista();
}
