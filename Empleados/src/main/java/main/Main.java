package main;

import comunicacion.ComunicacionImpl;
import comunicacion.configuracion.ConfiguracionComunicacionXML;
import controlador.Controlador;
import empleado.SesionImpl;
import empleado.configuracion.ConfiguracionEmpleadoXML;
import vistas.JFrame.VistaEmpleadoImpl;

public class Main {

	private static final String COMUNICACION_PATH = "comunicacionConfig.xml";
	private static final String EMPLEADO_PATH = "empleadoConfig.xml";

	public static void main(String[] args) {
		Controlador controlador = new Controlador(
				new VistaEmpleadoImpl(),
				new ComunicacionImpl(new ConfiguracionComunicacionXML(COMUNICACION_PATH)),
				new SesionImpl(new ConfiguracionEmpleadoXML(EMPLEADO_PATH)));
	}
}
