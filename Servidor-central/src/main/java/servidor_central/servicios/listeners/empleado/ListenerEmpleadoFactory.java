package servidor_central.servicios.listeners.empleado;

import dependencias.interfaces.filaDeEspera.OperacionesEmpleado;
import dependencias.interfaces.televisor.ServicioVisualizacion;
import servidor_central.configuracion.ConfiguracionComunicacionServer;

public class ListenerEmpleadoFactory {

    private static final ListenerEmpleadoFactory INSTANCE = new ListenerEmpleadoFactory();

    private ListenerEmpleadoFactory() {

    }

    public static ListenerEmpleadoFactory getInstance() {
        return INSTANCE;
    }

    public ListenerEmpleado crearListenerEmpleado(OperacionesEmpleado operacionesEmpleado, ConfiguracionComunicacionServer configuracionComunicacionServer, ServicioVisualizacion servicioVisualizacion) {
        return new ListenerEmpleado(operacionesEmpleado, configuracionComunicacionServer.getPuertoEmpleado(), servicioVisualizacion);
    }
}
