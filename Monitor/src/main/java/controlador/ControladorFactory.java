package controlador;

import configuracion.ConfiguracionMonitor;
import dependencias.interfaces.monitor.Monitoreo;

public class ControladorFactory {

    private static final ControladorFactory INSTANCE = new ControladorFactory();

    private ControladorFactory() {

    }

    public static ControladorFactory getInstance() {
        return INSTANCE;
    }

    public Controlador crearControlador(String host, Monitoreo monitoreo, ConfiguracionMonitor configuracionMonitor) {
        return new Controlador(monitoreo,host,configuracionMonitor.getPuertosMonitoreo());
    }
}
