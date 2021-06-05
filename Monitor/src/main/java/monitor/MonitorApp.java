package monitor;

import monitor.comunicacion.MonitoreoImpl;
import monitor.configuracion.ConfiguracionMonitor;
import monitor.configuracion.XML.ConfiguracionMonitorImpl;
import monitor.controlador.Controlador;
import monitor.comunicacion.MonitoreoFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MonitorApp {

    private static final String CONFIGURACION_MONITOR_PATH = "configuracionMonitor.xml";
    private static final Integer TIEMPO_ESPERA = 1000;
    private static final Integer TIEMPO_MONITOREO = 10000;

    public static void main(String[] args) {

        try {
            ConfiguracionMonitor configuracionMonitor = cargarConfiguracionMonitor();
            MonitoreoImpl monitoreo = MonitoreoFactory.getInstance().crearMonitoreo(InetAddress.getLocalHost().getHostAddress(),
                    configuracionMonitor, TIEMPO_ESPERA);
            Controlador controlador = new Controlador(monitoreo, TIEMPO_MONITOREO);
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
