package empleado.vistas;

import java.awt.event.ActionListener;

import dependencias.atencion.Atencion;
import empleado.sesion.Sesion;

public interface VistaEmpleado {
	
	void cancelarAtencion();
	void confirmarAtencion(Atencion atencion);
	void finalizarAtencion();
	void asignarAtencion(Atencion atencion);
	void anularAtencion();
	void setActionListener(ActionListener actionListener);
	void informarMensaje(String mensaje);
	void inicializarVista(Sesion sesion);

}
