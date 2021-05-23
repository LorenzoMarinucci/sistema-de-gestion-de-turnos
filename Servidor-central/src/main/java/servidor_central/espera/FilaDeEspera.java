package servidor_central.espera;

import dependencias.atencion.Atencion;
import dependencias.mensajes.totem.Registro;

import java.util.Iterator;
import java.util.Optional;

public interface FilaDeEspera {

    Registro agregarAtencion(Integer DNI);
    Optional<Atencion> sacarNuevaAtencion();
    void reingresarAtencion(Atencion atencion);
    void establecerFila(Iterator<Atencion> atenciones);
    Iterator<Atencion> obtenerFila();

}
