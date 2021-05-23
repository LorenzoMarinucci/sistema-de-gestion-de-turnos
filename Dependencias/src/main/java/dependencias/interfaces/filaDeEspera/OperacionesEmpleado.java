package dependencias.interfaces.filaDeEspera;

import dependencias.atencion.Atencion;

import java.util.Optional;

public interface OperacionesEmpleado {

    Atencion solicitarAtencion(Integer numeroDeBox) throws Exception;
    void cancelarAtencion(Atencion atencion) throws Exception;
    void anularAtencion(Atencion atencion) throws Exception;
    void finalizarAtencion(Atencion atencion) throws Exception;
    Atencion confirmarAtencion(Atencion atencion) throws Exception;

}
