package servicios;

import dependencias.atencion.Atencion;
import excepciones.LlamadoNoEncontradoException;

public interface ServicioVisualizacion {
	
	void mostrarAtencion(Atencion atencion);
	void quitarAtencion(Atencion atencion) throws LlamadoNoEncontradoException;
	void inicializar();

}
