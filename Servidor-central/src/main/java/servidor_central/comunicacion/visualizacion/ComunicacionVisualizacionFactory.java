package servidor_central.comunicacion.visualizacion;

import dependencias.mensajes.televisor.SolicitudTelevisorFactory;
import servidor_central.configuracion.ConfiguracionComunicacionServer;

public class ComunicacionVisualizacionFactory {

    private static final ComunicacionVisualizacionFactory INSTANCE = new ComunicacionVisualizacionFactory();

    private ComunicacionVisualizacionFactory() {

    }

    public static ComunicacionVisualizacionFactory getInstance() {
        return INSTANCE;
    }

    public ComunicacionVisualizacion crearComunicacionVisualizacion(String host,
                                                                    ConfiguracionComunicacionServer configuracionComunicacionServer,
                                                                    SolicitudTelevisorFactory solicitudTelevisorFactory) {
        return new ComunicacionVisualizacion(host, configuracionComunicacionServer.getPuertoTelevisor(),
                solicitudTelevisorFactory);
    }

}
