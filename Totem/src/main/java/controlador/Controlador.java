package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import comunicacion.Comunicacion;
import comunicacion.InformeRegistro;
import utilidades.Validador;
import vistas.*;

public class Controlador implements ActionListener {
	
	private final Integer TIEMPO_ESPERA_NUEVO_REGISTRO = 5000;

	private Vista UIBienvenida;
	private VistaRegistro UIRegistro;
	private VistaFin UIFin;
	private Validador validador;
	private Comunicacion comunicador;

	public Controlador(Vista UIBienvenida, VistaRegistro UIRegistro, VistaFin UIFin, Validador validador,
					   Comunicacion comunicador) {
		this.UIBienvenida = UIBienvenida;
		this.UIRegistro = UIRegistro;
		this.UIFin = UIFin;
		this.validador = validador;
		this.comunicador = comunicador;
		this.UIBienvenida.setActionListener(this);
		this.UIRegistro.setActionListener(this);
		this.UIFin.setActionListener(this);
		this.UIBienvenida.abrirVista();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("REGISTRARSE")) {
			UIBienvenida.cerrarVista();
			UIRegistro.abrirVista();
		} else if (command.equals("FINALIZAR REGISTRO")) {
			String DNI = UIRegistro.getDNI();
			if (validador.DNIesValido(DNI)) {
				procesarRegistro(DNI);
			}
			else {
				UIRegistro.DNInoValido();
			}
		} else if (command.equals("TIMEOUT FIN")) {
			UIFin.cerrarVista();
			UIBienvenida.abrirVista();
		}
	}
	
	private void procesarRegistro(String DNI) {
		UIRegistro.cerrarVista();
		//InformeRegistro informe = comunicador.enviarDNI(DNI);
		UIFin.abrirVista();
		UIFin.iniciarTimeout(TIEMPO_ESPERA_NUEVO_REGISTRO);
	}

}
