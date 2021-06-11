package servidor_central.servicios.espera.listeners.servidor;

import dependencias.interfaces.filaDeEspera.Sincronizacion;
import servidor_central.configuracion.ConfiguracionComunicacionServer;

public class ListenerSincronizacionFactory {

    private static final ListenerSincronizacionFactory INSTANCE = new ListenerSincronizacionFactory();

    private ListenerSincronizacionFactory() {

    }

    public static ListenerSincronizacionFactory getInstance() {
        return INSTANCE;
    }

    public ListenerSincronizacion crearListenerServidor(Sincronizacion sincronizacion, ConfiguracionComunicacionServer configuracionComunicacionServer) {
        return new ListenerSincronizacion(configuracionComunicacionServer.getPuertoSincronizar(), sincronizacion);
    }

}
