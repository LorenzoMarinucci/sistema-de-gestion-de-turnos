package monitor.comunicacion;

import monitor.configuracion.ConfiguracionMonitor;

public class MonitoreoFactory {

    private static final MonitoreoFactory INSTANCE = new MonitoreoFactory();

    private MonitoreoFactory() {

    }

    public static MonitoreoFactory getInstance() {
        return INSTANCE;
    }

    public MonitoreoImpl crearMonitoreo(String host, ConfiguracionMonitor configuracionMonitor, Integer tiempoEspera) {
        return new MonitoreoImpl(host, configuracionMonitor.getPuertosMonitoreo(),tiempoEspera);
    }
}
