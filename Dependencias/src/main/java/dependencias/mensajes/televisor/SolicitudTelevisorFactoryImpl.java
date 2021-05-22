package dependencias.mensajes.televisor;

import dependencias.atencion.Atencion;

public class SolicitudTelevisorFactoryImpl implements SolicitudTelevisorFactory {

    private static final SolicitudTelevisorFactoryImpl INSTANCE = new SolicitudTelevisorFactoryImpl();

    private SolicitudTelevisorFactoryImpl() {}

    public static SolicitudTelevisorFactoryImpl getInstance() {
        return INSTANCE;
    }

    public SolicitudTelevisor nuevaSolicitudMostrar(Atencion atencion) {
        return new SolicitudTelevisor("MOSTRAR", atencion);
    }

    public SolicitudTelevisor nuevaSolicitudQuitar(Atencion atencion) {
        return new SolicitudTelevisor("QUITAR", atencion);
    }

    /* public SolicitudTelevisor nuevaSolicitudInicializar() {
        return new SolicitudTelevisor("INICIAR", null);
    } */

}
