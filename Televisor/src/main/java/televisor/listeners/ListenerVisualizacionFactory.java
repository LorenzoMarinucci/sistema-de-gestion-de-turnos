package televisor.listeners;

import dependencias.interfaces.televisor.ServicioVisualizacion;
import televisor.configuracion.ConfiguracionComunicacionTelevisor;

public class ListenerVisualizacionFactory {

    private static final ListenerVisualizacionFactory INSTANCE = new ListenerVisualizacionFactory();

    private ListenerVisualizacionFactory() {

    }

    public static ListenerVisualizacionFactory getInstance() {
        return INSTANCE;
    }

    public ListenerVisualizacion crearListenerVisualizacion(ConfiguracionComunicacionTelevisor configuracionComunicacionTelevisor,
                                                            ServicioVisualizacion servicioVisualizacion) {
        return new ListenerVisualizacion(configuracionComunicacionTelevisor.getPuerto(),
                servicioVisualizacion);
    }
}
