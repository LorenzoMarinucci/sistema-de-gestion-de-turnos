package totem.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import dependencias.interfaces.filaDeEspera.RegistroTotem;
import dependencias.mensajes.totem.Registro;
import totem.utilidades.Validador;
import totem.vistas.*;

public class Controlador implements ActionListener {

	private final Integer TIEMPO_ESPERA_NUEVO_REGISTRO = 5000;
	private final Logger log = Logger.getLogger("log.totem.controlador");

	private Vista UIBienvenida;
	private VistaRegistro UIRegistro;
	private VistaFin UIFin;
	private Validador validador;
	private RegistroTotem registroTotem;

	public Controlador(Vista UIBienvenida, VistaRegistro UIRegistro, VistaFin UIFin, Validador validador, RegistroTotem registroTotem) {
		this.UIBienvenida = UIBienvenida;
		this.UIRegistro = UIRegistro;
		this.UIFin = UIFin;
		this.validador = validador;
		this.registroTotem = registroTotem;
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
			log.info("DANDO INICIO A NUEVO REGISTRO");
		} else if (command.equals("FINALIZAR REGISTRO")) {
			String DNI = UIRegistro.getDNI();
			if (validador.DNIesValido(DNI)) {
				procesarRegistro(DNI);
			}
			else {
				UIRegistro.DNInoValido();
				log.info("SE HA INTRODUCIDO UN DNI INVÁLIDO");
			}
		} else if (command.equals("TIMEOUT FIN")) {
			UIFin.cerrarVista();
			UIBienvenida.abrirVista();
			log.info("REGISTRO FINALIZADO");
		}
	}
	
	private void procesarRegistro(String DNI) {
		UIRegistro.cerrarVista();
		log.info("PROCESAMIENTO DE NUEVO REGISTRO. DNI: " + DNI);
		Registro informe = registroTotem.agregarAtencion(Integer.parseInt(DNI));
		if (informe.isRegistroExitoso()) {
			log.info("PROCESAMIENTO FINALIZADO");
		} else {
			log.info("REGISTRO FALLIDO. " + informe.getMensaje());
		}
		UIFin.abrirVista();
		UIFin.informarResultado(informe);
		UIFin.iniciarTimeout(TIEMPO_ESPERA_NUEVO_REGISTRO);
	}

}
