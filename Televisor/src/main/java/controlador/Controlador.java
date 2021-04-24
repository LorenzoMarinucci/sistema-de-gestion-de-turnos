package controlador;

import configuracion.Configuracion;
import configuracion.ConfiguracionXML;
import excepciones.LlamadoNoEncontradoException;
import servicios.ServicioVisualizacion;
import servicios.ServicioVisualizacionImpl;
import vistas.LlamadosImpl;
import vistas.VistaLlamados;

public class Controlador {
	
	private Configuracion configuracion;
	private ServicioVisualizacion servicioVisualizacion;
	
	public Controlador() {
		configuracion = new ConfiguracionXML();
		servicioVisualizacion = new ServicioVisualizacionImpl(configuracion.getLugaresTelevisor());
		servicioVisualizacion.inicializarVista();
	}
	
	public void cargarLlamado(String DNI, String box) {
		// conexion
		servicioVisualizacion.mostrarLlamado(DNI, box);
	}
	
	public void quitarLlamado(String DNI) {
		// conexion
		try {
			servicioVisualizacion.quitarLlamado(DNI);
		} catch (LlamadoNoEncontradoException e) {
			e.printStackTrace();
		}
	}
	
}
