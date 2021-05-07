package servidor_central.comunicacion;

import dependencias.interfaces.filaDeEspera.RegistroTotem;

public abstract class Listener {

    protected Integer port;

    public Listener(Integer port){
        this.port = port;
    }

    public abstract void iniciar();

}
