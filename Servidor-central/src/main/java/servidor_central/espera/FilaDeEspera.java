package servidor_central.espera;

import dependencias.atencion.Atencion;
import dependencias.atencion.Cliente;
import dependencias.mensajes.totem.Registro;
import servidor_central.excepciones.FilaDeEsperaException;

import java.util.Iterator;
import java.util.Optional;

public interface FilaDeEspera {

    void agregarAtencion(Cliente cliente) throws FilaDeEsperaException;
    Optional<Atencion> sacarNuevaAtencion();
    void reingresarAtencion(Atencion atencion);
    void establecerFila(Iterator<Atencion> atenciones);
    Iterator<Atencion> obtenerFila();

}
