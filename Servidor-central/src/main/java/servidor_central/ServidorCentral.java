package servidor_central;

import dependencias.atencion.Tipo;
import servidor_central.configuracion.ConfiguracionComunicacionServer;
import servidor_central.configuracion.ConfiguracionFilaDeEspera;
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
import java.util.HashMap;
import java.util.Map;

public class ServidorCentral {

    private static final String FILA_DE_ESPERA_PATH = "filaDeEsperaConfig.xml";
    private static final String COMUNICACION_PATH = "comunicacionConfig.xml";

    public static void main(String[] args) {
        Map<Tipo, Integer> prioridades = new HashMap<>();
        initPrioridades(prioridades);
        try {
            ConfiguracionComunicacionServer configuracionComunicacionServer = cargarConfiguracionComunicacion();
            ConfiguracionFilaDeEspera configuracionFilaDeEspera = cargarConfiguracionFilaDeEspera();
            ServicioEspera servicioEspera = new ServicioEsperaImpl(
                    new FilaDeEsperaPQ(configuracionFilaDeEspera.getTamañoFila(), prioridades));
            ListenerEmpleado listenerEmpleado = new ListenerEmpleado(
                    servicioEspera, InetAddress.getLocalHost().getHostAddress(),
                    configuracionComunicacionServer.getPuertoEmpleado());
            ListenerTotem listenerTotem = new ListenerTotem(
                    servicioEspera, InetAddress.getLocalHost().getHostAddress(),
                    configuracionComunicacionServer.getPuertoTotem());
        } catch (JAXBException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void initPrioridades(Map<Tipo, Integer> prioridades) {
        prioridades.put(Tipo.NUEVA, 0);
        prioridades.put(Tipo.REINGRESADA, 10);
    }

    private static ConfiguracionFilaDeEspera cargarConfiguracionFilaDeEspera() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ConfiguracionFilaDeEspera.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        ConfiguracionFilaDeEspera configuracionFilaDeEspera = (ConfiguracionFilaDeEspera)
                jaxbUnmarshaller.unmarshal(new File(FILA_DE_ESPERA_PATH));
        return configuracionFilaDeEspera;
    }

    private static ConfiguracionComunicacionServer cargarConfiguracionComunicacion() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ConfiguracionComunicacionServer.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        ConfiguracionComunicacionServer configuracionComunicacionServer = (ConfiguracionComunicacionServer)
                jaxbUnmarshaller.unmarshal(new File(COMUNICACION_PATH));
        return configuracionComunicacionServer;
    }

}
