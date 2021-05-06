package servidor_central;

import dependencias.atencion.Tipo;
import servidor_central.comunicacion.ComunicacionTelevisor;
import servidor_central.comunicacion.TCP.ComunicacionTelevisorImpl;
import servidor_central.configuracion.XML.ConfiguracionComunicacionServerImpl;
import servidor_central.configuracion.XML.ConfiguracionFilaDeEsperaImpl;
import servidor_central.espera.Queue.FilaDeEsperaPQ;
import servidor_central.listeners.ListenerEmpleado;
import servidor_central.listeners.ListenerTotem;
import servidor_central.servicios.ServicioEspera;
import servidor_central.servicios.ServicioEsperaImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

public class ServidorCentral {

    private static final String FILA_DE_ESPERA_PATH = "filaDeEsperaConfig.xml";
    private static final String COMUNICACION_PATH = "comunicacionConfig.xml";

    public static void main(String[] args) {
        try {
            ConfiguracionComunicacionServerImpl configuracionComunicacionServer = cargarConfiguracionComunicacion();
            ConfiguracionFilaDeEsperaImpl configuracionFilaDeEspera = cargarConfiguracionFilaDeEspera();
            Map<String, Integer> prioridades = configuracionFilaDeEspera.getPrioridades();
            ComunicacionTelevisor comunicacionTelevisor = new ComunicacionTelevisorImpl(InetAddress.getLocalHost().getHostAddress(),
                    configuracionComunicacionServer);
            ServicioEspera servicioEspera = new ServicioEsperaImpl(
                    new FilaDeEsperaPQ(configuracionFilaDeEspera));
            ListenerEmpleado listenerEmpleado = new ListenerEmpleado(
                    servicioEspera, comunicacionTelevisor,
                    configuracionComunicacionServer.getPuertoEmpleado());
            ListenerTotem listenerTotem = new ListenerTotem(
                    servicioEspera,
                    configuracionComunicacionServer.getPuertoTotem());
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
