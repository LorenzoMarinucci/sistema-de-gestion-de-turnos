package servidor_central.espera;

import dependencias.atencion.Atencion;
import dependencias.atencion.Cliente;
import dependencias.mensajes.totem.Registro;

import java.util.Iterator;
import java.util.Optional;

public interface FilaDeEspera {

    Registro agregarAtencion(Cliente cliente);
    Optional<Atencion> sacarNuevaAtencion();
    void reingresarAtencion(Atencion atencion);
    void establecerFila(Iterator<Atencion> atenciones);
    Iterator<Atencion> obtenerFila();

}
