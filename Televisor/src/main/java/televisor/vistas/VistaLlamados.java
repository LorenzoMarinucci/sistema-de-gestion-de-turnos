package televisor.vistas;

import dependencias.atencion.Atencion;

public interface VistaLlamados {

	void cargarLlamado(Atencion atencion, Integer posicion);
	void quitarLlamado(Integer posicion);

}
