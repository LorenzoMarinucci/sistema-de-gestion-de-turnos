package servidor_central.servicios.listeners;

public abstract class Listener {

    protected Integer port;

    public Listener(Integer port){
        this.port = port;
    }

    public abstract void iniciar();

}
