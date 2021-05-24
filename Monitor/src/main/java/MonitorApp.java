import comunicacion.MonitoreoImpl;
import configuracion.ConfiguracionMonitor;
import configuracion.XML.ConfiguracionMonitorImpl;
import controlador.Controlador;
import controlador.ControladorFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MonitorApp {

    private static final String CONFIGURACION_MONITOR_PATH = "configuracionMonitor.xml";
    private static final Integer TIEMPO_ESPERA = 1000;

    public static void main(String[] args) {

        try {
            ConfiguracionMonitor configuracionMonitor = cargarConfiguracionMonitor();
            Controlador controlador = ControladorFactory.getInstance().crearControlador(InetAddress.getLocalHost().getHostAddress(),
                    new MonitoreoImpl(TIEMPO_ESPERA), configuracionMonitor);
        } catch (UnknownHostException | JAXBException e) {
            e.printStackTrace();
        }

    }

    private static ConfiguracionMonitor cargarConfiguracionMonitor() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ConfiguracionMonitorImpl.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        ConfiguracionMonitor configuracionMonitor = (ConfiguracionMonitorImpl)
                jaxbUnmarshaller.unmarshal(new File(CONFIGURACION_MONITOR_PATH));
        return configuracionMonitor;
    }

}
