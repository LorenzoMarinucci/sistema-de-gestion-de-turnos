package dependencias.mensajes.televisor;

import dependencias.atencion.Atencion;

public class SolicitudTelevisorFactory {

    public static SolicitudTelevisor  nuevaSolicitudMostrar(Atencion atencion) {
        return new SolicitudTelevisor("MOSTRAR", atencion);
    }

    public static SolicitudTelevisor nuevaSolicitudQuitar(Atencion atencion) {
        return new SolicitudTelevisor("QUITAR", atencion);
    }

}
