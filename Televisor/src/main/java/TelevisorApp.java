import configuracion.ConfiguracionComunicacion;
import configuracion.ConfiguracionTelevisor;
import listeners.ListenerServidor;
import servicios.ServicioVisualizacion;
import servicios.ServicioVisualizacionImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class TelevisorApp {

    private static final String COMUNICACION_PATH = "comunicacionConfig.xml";
    private static final String TELEVISOR_CONFIG_PATH = "televisorConfig.xml";

    public static void main(String[] args) {
        try {
            ConfiguracionComunicacion configuracionComunicacion = cargarConfiguracionComunicacion();
            ConfiguracionTelevisor configuracionTelevisor = cargarConfiguracionTelevisor();
            ServicioVisualizacion servicioVisualizacion = new ServicioVisualizacionImpl(configuracionTelevisor.getLugares());
            ListenerServidor listenerServidor = new ListenerServidor(
                    configuracionComunicacion.getPuerto(), servicioVisualizacion);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static ConfiguracionComunicacion cargarConfiguracionComunicacion() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ConfiguracionComunicacion.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        ConfiguracionComunicacion configuracionComunicacion = (ConfiguracionComunicacion)
                jaxbUnmarshaller.unmarshal(new File(COMUNICACION_PATH));
        return configuracionComunicacion;
    }

    private static ConfiguracionTelevisor cargarConfiguracionTelevisor() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ConfiguracionTelevisor.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        ConfiguracionTelevisor configuracionTelevisor = (ConfiguracionTelevisor)
                jaxbUnmarshaller.unmarshal(new File(TELEVISOR_CONFIG_PATH));
        return configuracionTelevisor;
    }
}
