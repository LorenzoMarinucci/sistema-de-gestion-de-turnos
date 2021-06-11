package servidor_central;

import dependencias.interfaces.filaDeEspera.Sincronizacion;
import dependencias.interfaces.televisor.ServicioVisualizacion;
import dependencias.mensajes.televisor.SolicitudTelevisorFactoryImpl;
import dependencias.mensajes.totem.RegistroFactoryImpl;
import servidor_central.comunicacion.sincronizacion.ComunicacionSincronizacionFactory;
import servidor_central.comunicacion.visualizacion.ComunicacionVisualizacionFactory;
import servidor_central.configuracion.XML.ConfiguracionComunicacionServerImpl;
import servidor_central.configuracion.XML.ConfiguracionFilaDeEsperaImpl;
import servidor_central.configuracion.XML.ConfiguracionRegistroActividadImpl;
import servidor_central.configuracion.XML.ConfiguracionServicioClientesImpl;
import servidor_central.espera.queue.FilaDeEsperaFactory;
import servidor_central.servicios.clientes.ServicioClientesFactory;
import servidor_central.servicios.espera.ServicioEsperaImpl;
import servidor_central.servicios.espera.listeners.empleado.ListenerEmpleado;
import servidor_central.servicios.espera.listeners.empleado.ListenerEmpleadoFactory;
import servidor_central.servicios.espera.listeners.monitor.ListenerMonitor;
import servidor_central.servicios.espera.listeners.monitor.ListenerMonitorFactory;
import servidor_central.servicios.espera.listeners.servidor.ListenerSincronizacion;
import servidor_central.servicios.espera.listeners.servidor.ListenerSincronizacionFactory;
import servidor_central.servicios.espera.listeners.totem.ListenerTotem;
import servidor_central.servicios.espera.listeners.totem.ListenerTotemFactory;
import servidor_central.servicios.registro.ServicioRegistroFactory;
import servidor_central.servicios.registro.ServicioRegistroImpl;

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
    private static final String CLIENTES_PATH = "servicioClientesConfig.xml";
    private static final String REGISTRO_PATH = "registroActividadConfig.xml";

    public static void main(String[] args) {
        try {
            ConfiguracionComunicacionServerImpl configuracionComunicacionServer = cargarConfiguracionComunicacion();
            ConfiguracionFilaDeEsperaImpl configuracionFilaDeEspera = cargarConfiguracionFilaDeEspera();
            ConfiguracionServicioClientesImpl configuracionServicioClientes = cargarConfiguracionServicioClientes();
            ConfiguracionRegistroActividadImpl configuracionRegistroActividad = cargarConfiguracionRegistroActividad();
            ServicioRegistroImpl servicioRegistro = ServicioRegistroFactory.getInstance().crearServicioRegistro(configuracionRegistroActividad);
            ServicioVisualizacion servicioVisualizacion = ComunicacionVisualizacionFactory.getInstance().crearComunicacionVisualizacion(
                    InetAddress.getLocalHost().getHostAddress(),
                    configuracionComunicacionServer,
                    SolicitudTelevisorFactoryImpl.getInstance()
            );
            Sincronizacion sincronizacion = ComunicacionSincronizacionFactory.getInstance().crearComunicacionSincronizacion(configuracionComunicacionServer,
                    InetAddress.getLocalHost().getHostAddress());
            ServicioEsperaImpl servicioEspera = new ServicioEsperaImpl(
                    FilaDeEsperaFactory.getInstance().crearFilaDeEspera(configuracionFilaDeEspera, sincronizacion),
                    ServicioClientesFactory.getInstance().crearServicioClientes(configuracionServicioClientes),
                    RegistroFactoryImpl.getInstance());
            ListenerEmpleado listenerEmpleado = ListenerEmpleadoFactory.getInstance().crearListenerEmpleado(
                    servicioEspera,
                    configuracionComunicacionServer,
                    servicioVisualizacion,
                    servicioRegistro);
            ListenerTotem listenerTotem = ListenerTotemFactory.getInstance().crearListenerTotem(
                    servicioEspera, configuracionComunicacionServer, servicioRegistro);
            ListenerSincronizacion listenerServidor = ListenerSincronizacionFactory.getInstance().crearListenerServidor(servicioEspera, configuracionComunicacionServer);
            ListenerMonitor listenerMonitor = ListenerMonitorFactory.getInstance().crearListenerMonitor(configuracionComunicacionServer, servicioEspera);
            listenerEmpleado.iniciar();
            listenerTotem.iniciar();
            listenerServidor.iniciar();
            listenerMonitor.iniciar();
        } catch (JAXBException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static ConfiguracionServicioClientesImpl cargarConfiguracionServicioClientes() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ConfiguracionServicioClientesImpl.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        ConfiguracionServicioClientesImpl configuracionServicioClientes = (ConfiguracionServicioClientesImpl)
                jaxbUnmarshaller.unmarshal(new File(CLIENTES_PATH));
        return configuracionServicioClientes;
    }

    private static ConfiguracionRegistroActividadImpl cargarConfiguracionRegistroActividad() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ConfiguracionRegistroActividadImpl.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        ConfiguracionRegistroActividadImpl configuracionRegistroActividad = (ConfiguracionRegistroActividadImpl)
                jaxbUnmarshaller.unmarshal(new File(REGISTRO_PATH));
        return configuracionRegistroActividad;
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
