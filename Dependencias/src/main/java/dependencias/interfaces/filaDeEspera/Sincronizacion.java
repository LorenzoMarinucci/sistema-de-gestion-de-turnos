package dependencias.interfaces.filaDeEspera;

import dependencias.atencion.Atencion;

import java.util.Iterator;

public interface Sincronizacion {

    Iterator<Atencion> obtenerAtenciones();

}
