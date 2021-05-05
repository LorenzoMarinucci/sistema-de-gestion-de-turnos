package empleado.comunicacion;

import dependencias.atencion.Atencion;
import empleado.excepciones.SolicitudException;

public interface Comunicacion {

    Atencion solicitarAtencion(Integer box) throws SolicitudException;
    void cancelarAtencion(Atencion atencion) throws SolicitudException;

}
