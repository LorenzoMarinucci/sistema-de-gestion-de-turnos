package servidor_central.servicios.clientes;

import servidor_central.configuracion.ConfiguracionServicioClientes;

public class ServicioClientesFactory {

    private static final ServicioClientesFactory INSTANCE = new ServicioClientesFactory();

    private ServicioClientesFactory() {

    }

    public static ServicioClientesFactory getInstance() {
        return INSTANCE;
    }

    public ServicioClientesImpl crearServicioClientes(ConfiguracionServicioClientes configuracionServicioClientes) {
        return new ServicioClientesImpl(configuracionServicioClientes.getPath());
    }

}
