package empleado.comunicacion;

import dependencias.atencion.Atencion;
import empleado.excepciones.SolicitudException;

public interface Comunicacion {

    Atencion solicitarAtencion(Integer box) throws SolicitudException;
    void cancelarAtencion(Atencion atencion) throws SolicitudException;
    void anularAtencion(Atencion atencion) throws SolicitudException;
    void confirmarAtencion(Atencion atencion) throws SolicitudException;
    void finalizarAtencion(Atencion atencion) throws SolicitudException;

}
