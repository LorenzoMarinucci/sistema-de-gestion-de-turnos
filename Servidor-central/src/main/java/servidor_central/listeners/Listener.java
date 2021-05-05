package servidor_central.listeners;

import servidor_central.servicios.ServicioEspera;

public abstract class Listener {

    protected ServicioEspera servicioEspera;
    protected String host;
    protected Integer port;

    public Listener(ServicioEspera servicioEspera, Integer port) {
        this.servicioEspera = servicioEspera;
        this.port = port;
    }

}
