package servidor_central.estado;

import servidor_central.configuracion.ConfiguracionComunicacionServer;

public class EstadoServidorImpl implements EstadoServidor{

    private Boolean primario;

    public EstadoServidorImpl(ConfiguracionComunicacionServer configuracionComunicacionServer) {
        this.primario = configuracionComunicacionServer.getPrimario();
    }

    @Override
    public Boolean isPrimario() {
        return primario;
    }

    @Override
    public void establecerSecundario() {
        this.primario = false;
    }

    @Override
    public void establecerPrimario() {
        this.primario = true;
    }
}
