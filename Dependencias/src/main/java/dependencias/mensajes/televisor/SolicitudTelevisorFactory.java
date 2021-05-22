package dependencias.mensajes.televisor;

import dependencias.atencion.Atencion;

public interface SolicitudTelevisorFactory {

    public SolicitudTelevisor nuevaSolicitudMostrar(Atencion atencion);

    public SolicitudTelevisor nuevaSolicitudQuitar(Atencion atencion);

    //public SolicitudTelevisor nuevaSolicitudInicializar();

}
