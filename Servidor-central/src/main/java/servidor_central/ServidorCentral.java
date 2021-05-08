package servidor_central;

import dependencias.interfaces.televisor.ServicioVisualizacion;
import servidor_central.comunicacion.ComunicacionVisualizacion;
import servidor_central.configuracion.XML.ConfiguracionComunicacionServerImpl;
import servidor_central.configuracion.XML.ConfiguracionFilaDeEsperaImpl;
import servidor_central.espera.queue.FilaDeEsperaPQ;
import servidor_central.servicios.ServicioEsperaImpl;
import servidor_central.servicios.listeners.ListenerEmpleado;
import servidor_central.servicios.listeners.ListenerTotem;

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
            ServicioVisualizacion servicioVisualizacion = new ComunicacionVisualizacion(InetAddress.getLocalHost().getHostAddress(), configuracionComunicacionServer);
            ServicioEsperaImpl servicioEspera = new ServicioEsperaImpl(
                    new FilaDeEsperaPQ(configuracionFilaDeEspera), servicioVisualizacion);
            ListenerEmpleado listenerEmpleado = new ListenerEmpleado(
                    servicioEspera,
                    configuracionComunicacionServer.getPuertoEmpleado());
            ListenerTotem listenerTotem = new ListenerTotem(
                    servicioEspera,
                    configuracionComunicacionServer.getPuertoTotem());
            listenerEmpleado.iniciar();
            listenerTotem.iniciar();
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
