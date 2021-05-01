package vistas;

import java.awt.event.ActionListener;

import atencion.Atencion;

public interface VistaEmpleado {
	
	void cancelarAtencion();
	void confirmarAtencion();
	void finalizarAtencion();
	void asignarAtencion(Atencion atencion);
	void anularAtencion();
	void setActionListener(ActionListener actionListener);

}
