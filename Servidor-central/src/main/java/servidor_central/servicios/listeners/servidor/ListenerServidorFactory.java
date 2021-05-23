package servidor_central.servicios.listeners.servidor;

import dependencias.interfaces.filaDeEspera.Sincronizacion;
import servidor_central.configuracion.ConfiguracionComunicacionServer;
import servidor_central.servicios.listeners.totem.ListenerTotem;

public class ListenerServidorFactory {

    private static final ListenerServidorFactory INSTANCE = new ListenerServidorFactory();

    private ListenerServidorFactory() {

    }

    public static ListenerServidorFactory getInstance() {
        return INSTANCE;
    }

    public ListenerServidor crearListenerServidor(Sincronizacion sincronizacion, ConfiguracionComunicacionServer configuracionComunicacionServer) {
        return new ListenerServidor(configuracionComunicacionServer.getPuertoSincronizar(), sincronizacion);
    }

}
