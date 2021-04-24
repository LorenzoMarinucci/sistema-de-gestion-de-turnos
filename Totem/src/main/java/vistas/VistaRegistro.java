package vistas;

import java.awt.event.ActionListener;

public interface VistaRegistro extends ActionListener {
	
	void setActionListener(ActionListener actionListener);
	void cerrarVista();
	void DNInoValido();
	String getDNI();

}
