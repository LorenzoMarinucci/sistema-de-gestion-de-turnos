package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utilidades.Validador;
import vistas.BienvenidaImpl;
import vistas.FinImpl;
import vistas.RegistroImpl;
import vistas.VistaBienvenida;
import vistas.VistaFin;
import vistas.VistaRegistro;

public class Controlador implements ActionListener {
	
	private final Long TIEMPO_ESPERA_NUEVO_REGISTRO = 5000L;

	private VistaBienvenida UIBienvenida;
	private VistaRegistro UIRegistro;
	private VistaFin UIFin;

	public Controlador() {
		UIBienvenida = new BienvenidaImpl();
		UIBienvenida.setActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Registrarse")) {
			UIBienvenida.cerrarVista();
			UIRegistro = new RegistroImpl();
			UIRegistro.setActionListener(this);
		} else if (command.equals("Finalizar registro")) {
			String DNI = UIRegistro.getDNI();
			if (Validador.DNIesValido(DNI)) {
				procesarRegistro();
				// envio mensaje
			}
			else {
				UIRegistro.DNInoValido();
			}
		}
	}
	
	private void procesarRegistro() {
		UIRegistro.cerrarVista();
		UIFin = new FinImpl();
		UIFin.setActionListener(this);
		try {
			Thread.sleep(TIEMPO_ESPERA_NUEVO_REGISTRO);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		UIFin.cerrarVista();
		UIBienvenida = new BienvenidaImpl();
		UIBienvenida.setActionListener(this);
	}

}
