package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vistas.VistaEmpleado;

public class Controlador implements ActionListener {
	
	private VistaEmpleado vistaEmpleado;
	
	public Controlador(VistaEmpleado vistaEmpleado) {
		this.vistaEmpleado = vistaEmpleado;
		vistaEmpleado.setActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Finalizar")) {
			
		} else if (command.equals("Siguiente")) {
			
		} else if (command.equals("Anular")) {
			
		} else if (command.equals("Cancelar")) {
			
		} else if (command.equals("Confirmar")) {
			
		}
	}
	
	

}
