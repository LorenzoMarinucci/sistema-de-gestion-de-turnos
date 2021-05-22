package servidor_central.servicios.listeners.totem;

import dependencias.interfaces.filaDeEspera.RegistroTotem;
import servidor_central.configuracion.ConfiguracionComunicacionServer;
import servidor_central.estado.EstadoServidor;

public class ListenerTotemFactory {

    private static final ListenerTotemFactory INSTANCE = new ListenerTotemFactory();

    private ListenerTotemFactory() {

    }

    public static ListenerTotemFactory getInstance() {
        return INSTANCE;
    }

    public ListenerTotem crearListenerTotem(RegistroTotem registroTotem,
                                            ConfiguracionComunicacionServer configuracionComunicacionServer,
                                            EstadoServidor estadoServidor) {
        return new ListenerTotem(registroTotem, configuracionComunicacionServer.getPuertoTotem(),
                estadoServidor);
    }

}
