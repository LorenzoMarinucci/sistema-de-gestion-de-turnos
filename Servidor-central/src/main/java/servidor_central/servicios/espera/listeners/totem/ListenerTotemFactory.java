package servidor_central.servicios.espera.listeners.totem;

import dependencias.interfaces.filaDeEspera.RegistroTotem;
import servidor_central.configuracion.ConfiguracionComunicacionServer;
import servidor_central.servicios.registro.ServicioRegistro;

public class ListenerTotemFactory {

    private static final ListenerTotemFactory INSTANCE = new ListenerTotemFactory();

    private ListenerTotemFactory() {

    }

    public static ListenerTotemFactory getInstance() {
        return INSTANCE;
    }

    public ListenerTotem crearListenerTotem(RegistroTotem registroTotem,
                                            ConfiguracionComunicacionServer configuracionComunicacionServer,
                                            ServicioRegistro servicioRegistro) {
        return new ListenerTotem(registroTotem, configuracionComunicacionServer.getPuertoTotem(), servicioRegistro);
    }

}
