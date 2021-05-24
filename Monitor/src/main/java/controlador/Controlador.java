package controlador;

import dependencias.interfaces.monitor.Monitoreo;

import java.util.List;
import java.util.logging.Logger;

public class Controlador {

    private final Integer TIEMPO_REVISION = 5000;

    private Logger log = Logger.getLogger("log.monitor.Controlador");

    private Monitoreo monitoreo;
    private String host;
    private List<Integer> puertos;

    public Controlador(Monitoreo monitoreo, String host, List<Integer> puertos) {
        this.monitoreo = monitoreo;
        this.host = host;
        this.puertos = puertos;
        iniciarMonitoreo();
    }

    private void iniciarMonitoreo() {
        log.info("INICIANDO MONITOREO");
        new Thread(() -> {
            while (true) {
            for (Integer puerto:puertos) {
                if (monitoreo.obtenerRespuesta(host,puerto)) {
                    log.info("MONITOREO EXITOSO DEL PUERTO " + puerto);
                } else {
                    log.info("MONITOREO FALLIDO DEL PUERTO " + puerto);
                }
            }
            try {
                Thread.sleep(TIEMPO_REVISION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }}).start();
    }

}
