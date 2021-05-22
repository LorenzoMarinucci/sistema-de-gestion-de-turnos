package servidor_central.servicios.listeners.empleado;

import dependencias.interfaces.filaDeEspera.OperacionesEmpleado;
import servidor_central.configuracion.ConfiguracionComunicacionServer;
import servidor_central.estado.EstadoServidor;

public class ListenerEmpleadoFactory {

    private static final ListenerEmpleadoFactory INSTANCE = new ListenerEmpleadoFactory();

    private ListenerEmpleadoFactory() {

    }

    public static ListenerEmpleadoFactory getInstance() {
        return INSTANCE;
    }

    public ListenerEmpleado crearListenerEmpleado(OperacionesEmpleado operacionesEmpleado, ConfiguracionComunicacionServer configuracionComunicacionServer,
                                                  EstadoServidor estadoServidor) {
        return new ListenerEmpleado(operacionesEmpleado, configuracionComunicacionServer.getPuertoEmpleado(),
                estadoServidor);
    }
}
