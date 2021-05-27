package servidor_central.servicios.listeners.monitor;

import dependencias.interfaces.monitor.Monitoreo;
import servidor_central.configuracion.ConfiguracionComunicacionServer;

public class ListenerMonitorFactory {

    private static final ListenerMonitorFactory INSTANCE = new ListenerMonitorFactory();

    private ListenerMonitorFactory() {

    }

    public static ListenerMonitorFactory getInstance() {
        return INSTANCE;
    }

    public ListenerMonitor crearListenerMonitor(ConfiguracionComunicacionServer configuracionComunicacionServer, Monitoreo monitoreo) {
        return new ListenerMonitor(configuracionComunicacionServer.getPuertoMonitoreo(), monitoreo);
    }
}
