package vistas;

import java.awt.event.ActionListener;

public interface VistaFin extends ActionListener {
	
	void setActionListener(ActionListener actionListener);
	void cerrarVista();

}
