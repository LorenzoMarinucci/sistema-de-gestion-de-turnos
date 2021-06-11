package servidor_central.servicios.espera.listeners.empleado;

import dependencias.interfaces.filaDeEspera.OperacionesEmpleado;
import dependencias.interfaces.televisor.ServicioVisualizacion;
import servidor_central.configuracion.ConfiguracionComunicacionServer;
import servidor_central.servicios.registro.ServicioRegistro;

public class ListenerEmpleadoFactory {

    private static final ListenerEmpleadoFactory INSTANCE = new ListenerEmpleadoFactory();

    private ListenerEmpleadoFactory() {

    }

    public static ListenerEmpleadoFactory getInstance() {
        return INSTANCE;
    }

    public ListenerEmpleado crearListenerEmpleado(OperacionesEmpleado operacionesEmpleado,
                                                  ConfiguracionComunicacionServer configuracionComunicacionServer,
                                                  ServicioVisualizacion servicioVisualizacion, ServicioRegistro servicioRegistro) {
        return new ListenerEmpleado(operacionesEmpleado, configuracionComunicacionServer.getPuertoEmpleado(), servicioVisualizacion, servicioRegistro);
    }
}
