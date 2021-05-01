package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import atencion.Atencion;
import comunicacion.Comunicacion;
import empleado.SesionImpl;
import vistas.VistaEmpleado;

public class Controlador implements ActionListener {
	
	private VistaEmpleado vistaEmpleado;
	private SesionImpl sesion;
	private Comunicacion comunicacion;
	
	public Controlador(VistaEmpleado vistaEmpleado, Comunicacion comunicacion, SesionImpl sesion) {
		this.vistaEmpleado = vistaEmpleado;
		this.sesion = sesion;
		this.comunicacion = comunicacion;
		vistaEmpleado.setActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		Atencion atencion;
		if (command.equals("Finalizar")) {
			atencion = sesion.finalizarAtencion();
			vistaEmpleado.finalizarAtencion();
		} else if (command.equals("Siguiente")) {
			atencion = comunicacion.solicitarAtencion();
			sesion.asignarAtencion(atencion);
			vistaEmpleado.asignarAtencion(atencion);
		} else if (command.equals("Anular")) {
			atencion = sesion.finalizarAtencion();
			vistaEmpleado.anularAtencion();
		} else if (command.equals("Cancelar")) {
			atencion = sesion.cancelarAtencion();
			comunicacion.cancelarAtencion(atencion);
			vistaEmpleado.cancelarAtencion();
		} else if (command.equals("Confirmar")) {
			atencion = sesion.confirmarAtencion();
			vistaEmpleado.confirmarAtencion();
		}
	}
	
	

}
