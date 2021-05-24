package servidor_central.servicios.listeners.monitor;

import servidor_central.configuracion.ConfiguracionComunicacionServer;

public class ListenerMonitorFactory {

    private static final ListenerMonitorFactory INSTANCE = new ListenerMonitorFactory();

    private ListenerMonitorFactory() {

    }

    public static ListenerMonitorFactory getInstance() {
        return INSTANCE;
    }

    public ListenerMonitor crearListenerMonitor(ConfiguracionComunicacionServer configuracionComunicacionServer) {
        return new ListenerMonitor(configuracionComunicacionServer.getPuertoMonitoreo());
    }
}
