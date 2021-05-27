package controlador;

import dependencias.interfaces.monitor.Monitoreo;

import java.util.List;
import java.util.logging.Logger;

public class Controlador {

    private Integer tiempoMonitoreo;

    private Logger log = Logger.getLogger("log.monitor.Controlador");

    private Monitoreo monitoreo;

    public Controlador(Monitoreo monitoreo, Integer tiempoMonitoreo) {
        this.monitoreo = monitoreo;
        this.tiempoMonitoreo = tiempoMonitoreo;
        iniciarMonitoreo();
    }

    private void iniciarMonitoreo() {
        log.info("INICIANDO MONITOREO");
        new Thread(() -> {
            while (true) {
                monitoreo.obtenerRespuesta();
                try {
                    Thread.sleep(tiempoMonitoreo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
