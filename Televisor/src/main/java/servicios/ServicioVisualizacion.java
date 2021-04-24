package servicios;

import excepciones.LlamadoNoEncontradoException;

public interface ServicioVisualizacion {
	
	void mostrarLlamado(String DNI, String box);
	void quitarLlamado(String DNI) throws LlamadoNoEncontradoException;
	void inicializarVista();

}
