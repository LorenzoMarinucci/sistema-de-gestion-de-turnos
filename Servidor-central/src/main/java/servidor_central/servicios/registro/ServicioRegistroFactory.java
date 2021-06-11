package servidor_central.servicios.registro;

import servidor_central.configuracion.ConfiguracionRegistroActividad;

public class ServicioRegistroFactory {

    private static final ServicioRegistroFactory INSTANCE = new ServicioRegistroFactory();

    private ServicioRegistroFactory() {

    }

    public static ServicioRegistroFactory getInstance() {
        return INSTANCE;
    }

    public ServicioRegistroImpl crearServicioRegistro(ConfiguracionRegistroActividad configuracionRegistroActividad) {
        return new ServicioRegistroImpl(configuracionRegistroActividad.getPath());
    }

}
