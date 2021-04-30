package vistas;

import java.awt.event.ActionListener;

import atenciones.AtencionEmpleado;

public interface VistaEmpleado extends ActionListener {
	
	void cancelarAtencion();
	void confirmarAtencion(AtencionEmpleado atencionEmpleado);
	void finalizarAtencion();
	void asignarAtencion(AtencionEmpleado atencionEmpleado);
	void anularAtencion();
	void setActionListener(ActionListener actionListener);

}
