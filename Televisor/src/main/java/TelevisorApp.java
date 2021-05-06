import configuracion.XML.ConfiguracionComunicacionImpl;
import configuracion.XML.ConfiguracionTelevisorImpl;
import listeners.ListenerServidor;
import servicios.ServicioVisualizacion;
import servicios.ServicioVisualizacionImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class TelevisorApp {

    private static final String COMUNICACION_PATH = "comunicacionConfig.xml";
    private static final String TELEVISOR_CONFIG_PATH = "televisorConfig.xml";

    public static void main(String[] args) {
        try {
            ConfiguracionComunicacionImpl configuracionComunicacion = cargarConfiguracionComunicacion();
            ConfiguracionTelevisorImpl configuracionTelevisor = cargarConfiguracionTelevisor();
            ServicioVisualizacion servicioVisualizacion = new ServicioVisualizacionImpl(configuracionTelevisor);
            ListenerServidor listenerServidor = new ListenerServidor(
                    configuracionComunicacion, servicioVisualizacion);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static ConfiguracionComunicacionImpl cargarConfiguracionComunicacion() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ConfiguracionComunicacionImpl.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        ConfiguracionComunicacionImpl configuracionComunicacion = (ConfiguracionComunicacionImpl)
                jaxbUnmarshaller.unmarshal(new File(COMUNICACION_PATH));
        return configuracionComunicacion;
    }

    private static ConfiguracionTelevisorImpl cargarConfiguracionTelevisor() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ConfiguracionTelevisorImpl.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        ConfiguracionTelevisorImpl configuracionTelevisor = (ConfiguracionTelevisorImpl)
                jaxbUnmarshaller.unmarshal(new File(TELEVISOR_CONFIG_PATH));
        return configuracionTelevisor;
    }
}
