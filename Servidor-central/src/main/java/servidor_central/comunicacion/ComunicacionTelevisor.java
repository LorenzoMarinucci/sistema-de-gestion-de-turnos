package servidor_central.comunicacion;

import dependencias.atencion.Atencion;

public interface ComunicacionTelevisor {

    void enviarSolicitudMostrar(Atencion atencion);
    void enviarSolicitudQuitar(Atencion atencion);
}
