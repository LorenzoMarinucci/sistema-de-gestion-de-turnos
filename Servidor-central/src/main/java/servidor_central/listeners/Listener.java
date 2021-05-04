package servidor_central.listeners;

import servidor_central.servicios.ServicioEspera;

public abstract class Listener {

    protected ServicioEspera servicioEspera;
    protected String host;
    protected Integer port;

    public Listener(ServicioEspera servicioEspera, String host, Integer port) {
        this.servicioEspera = servicioEspera;
        this.host = host;
        this.port = port;
    }

}
