package servidor_central;

import dependencias.interfaces.filaDeEspera.Sincronizacion;
import dependencias.interfaces.televisor.ServicioVisualizacion;
import dependencias.mensajes.televisor.SolicitudTelevisorFactoryImpl;
import dependencias.mensajes.totem.RegistroFactoryImpl;
import servidor_central.comunicacion.sincronizacion.ComunicacionSincronizacionFactory;
import servidor_central.comunicacion.visualizacion.ComunicacionVisualizacionFactory;
import servidor_central.configuracion.XML.ConfiguracionComunicacionServerImpl;
import servidor_central.configuracion.XML.ConfiguracionFilaDeEsperaImpl;
import servidor_central.espera.queue.FilaDeEsperaFactory;
import servidor_central.servicios.ServicioEsperaImpl;
import servidor_central.servicios.listeners.empleado.ListenerEmpleado;
import servidor_central.servicios.listeners.empleado.ListenerEmpleadoFactory;
import servidor_central.servicios.listeners.monitor.ListenerMonitor;
import servidor_central.servicios.listeners.monitor.ListenerMonitorFactory;
import servidor_central.servicios.listeners.servidor.ListenerSincronizacion;
import servidor_central.servicios.listeners.servidor.ListenerSincronizacionFactory;
import servidor_central.servicios.listeners.totem.ListenerTotem;
import servidor_central.servicios.listeners.totem.ListenerTotemFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

public class ServidorCentral {

    private static final String FILA_DE_ESPERA_PATH = "filaDeEsperaConfig.xml";
    private static final String COMUNICACION_PATH = "comunicacionServerConfig.xml";

    public static void main(String[] args) {
        try {
            ConfiguracionComunicacionServerImpl configuracionComunicacionServer = cargarConfiguracionComunicacion();
            ConfiguracionFilaDeEsperaImpl configuracionFilaDeEspera = cargarConfiguracionFilaDeEspera();
            Map<String, Integer> prioridades = configuracionFilaDeEspera.getPrioridades();
            ServicioVisualizacion servicioVisualizacion = ComunicacionVisualizacionFactory.getInstance().crearComunicacionVisualizacion(
                    InetAddress.getLocalHost().getHostAddress(), configuracionComunicacionServer, SolicitudTelevisorFactoryImpl.getInstance()
            );
            Sincronizacion sincronizacion = ComunicacionSincronizacionFactory.getInstance().crearComunicacionSincronizacion(configuracionComunicacionServer, InetAddress.getLocalHost().getHostAddress());
            ServicioEsperaImpl servicioEspera = new ServicioEsperaImpl(
                    FilaDeEsperaFactory.getInstance().crearFilaDeEspera(configuracionFilaDeEspera, RegistroFactoryImpl.getInstance(), sincronizacion));
            ListenerEmpleado listenerEmpleado = ListenerEmpleadoFactory.getInstance().crearListenerEmpleado(
                    servicioEspera, configuracionComunicacionServer, servicioVisualizacion);
            ListenerTotem listenerTotem = ListenerTotemFactory.getInstance().crearListenerTotem(
                    servicioEspera, configuracionComunicacionServer
            );
            ListenerSincronizacion listenerServidor = ListenerSincronizacionFactory.getInstance().crearListenerServidor(servicioEspera, configuracionComunicacionServer);
            ListenerMonitor listenerMonitor = ListenerMonitorFactory.getInstance().crearListenerMonitor(configuracionComunicacionServer);
            listenerEmpleado.iniciar();
            listenerTotem.iniciar();
            listenerServidor.iniciar();
            listenerMonitor.iniciar();
        } catch (JAXBException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static ConfiguracionFilaDeEsperaImpl cargarConfiguracionFilaDeEspera() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ConfiguracionFilaDeEsperaImpl.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        ConfiguracionFilaDeEsperaImpl configuracionFilaDeEspera = (ConfiguracionFilaDeEsperaImpl)
                jaxbUnmarshaller.unmarshal(new File(FILA_DE_ESPERA_PATH));
        return configuracionFilaDeEspera;
    }

    private static ConfiguracionComunicacionServerImpl cargarConfiguracionComunicacion() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ConfiguracionComunicacionServerImpl.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        ConfiguracionComunicacionServerImpl configuracionComunicacionServer = (ConfiguracionComunicacionServerImpl)
                jaxbUnmarshaller.unmarshal(new File(COMUNICACION_PATH));
        return configuracionComunicacionServer;
    }

}
