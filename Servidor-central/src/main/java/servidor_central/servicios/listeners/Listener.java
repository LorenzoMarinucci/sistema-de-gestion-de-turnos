package servidor_central.servicios.listeners;

import servidor_central.estado.EstadoServidor;

import java.util.List;

public abstract class Listener {

    protected Integer portPrimario;
    protected List<Integer> portsSecundarios;
    protected EstadoServidor estadoServidor;

    public abstract void iniciar();
    protected void cambiarRol(Boolean primario) {
        if (primario)
            estadoServidor.establecerPrimario();
        else
            estadoServidor.establecerSecundario();
    }
}