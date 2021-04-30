package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import comunicacion.Comunicacion;
import comunicacion.InformeRegistro;
import utilidades.Validador;
import vistas.*;

public class Controlador implements ActionListener {
	
	private final Long TIEMPO_ESPERA_NUEVO_REGISTRO = 5000L;

	private Vista UIBienvenida;
	private VistaRegistro UIRegistro;
	private Vista UIFin;
	private Validador validador;
	private Comunicacion comunicador;

	public Controlador(Vista UIBienvenida, VistaRegistro UIRegistro, Vista UIFin, Validador validador,
					   Comunicacion comunicador) {
		this.UIBienvenida = UIBienvenida;
		this.UIRegistro = UIRegistro;
		this.UIFin = UIFin;
		this.validador = validador;
		this.comunicador = comunicador;
		UIBienvenida.abrirVista(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Registrarse")) {
			UIBienvenida.cerrarVista();
			UIRegistro.abrirVista(this);
		} else if (command.equals("Finalizar registro")) {
			String DNI = UIRegistro.getDNI();
			if (validador.DNIesValido(DNI)) {
				procesarRegistro(DNI);
			}
			else {
				UIRegistro.DNInoValido();
			}
		}
	}
	
	private void procesarRegistro(String DNI) {
		UIRegistro.cerrarVista();
		InformeRegistro informe = comunicador.enviarDNI(DNI);
		UIFin.abrirVista(this);
		try {
			Thread.sleep(TIEMPO_ESPERA_NUEVO_REGISTRO);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		UIFin.cerrarVista();
		UIBienvenida.abrirVista(this);
	}

}
