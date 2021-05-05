package vistas;

public interface VistaLlamados {

	Integer getUltimaPosicionLibre();
	void cargarLlamado(Integer DNI, Integer box, Integer posicion);
	void quitarLlamado(Integer posicion);

}
