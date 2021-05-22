package televisor;

import televisor.configuracion.XML.ConfiguracionComunicacionImpl;
import televisor.configuracion.XML.ConfiguracionTelevisorImpl;
import dependencias.interfaces.televisor.ServicioVisualizacion;
import televisor.listeners.ListenerVisualizacion;
import televisor.listeners.ListenerVisualizacionFactory;
import televisor.servicios.ServicioVisualizacionFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class TelevisorApp {

    private static final String COMUNICACION_PATH = "comunicacionTelevisorConfig.xml";
    private static final String TELEVISOR_CONFIG_PATH = "televisorConfig.xml";

    public static void main(String[] args) {
        try {
            ConfiguracionComunicacionImpl configuracionComunicacion = cargarConfiguracionComunicacion();
            ConfiguracionTelevisorImpl configuracionTelevisor = cargarConfiguracionTelevisor();
            ServicioVisualizacion servicioVisualizacion = ServicioVisualizacionFactory.getInstance().crearServicioVisualizacion(configuracionTelevisor);
            ListenerVisualizacion listenerVisualizacion = ListenerVisualizacionFactory.getInstance().crearListenerVisualizacion(
                    configuracionComunicacion, servicioVisualizacion
            );
            listenerVisualizacion.iniciar();
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