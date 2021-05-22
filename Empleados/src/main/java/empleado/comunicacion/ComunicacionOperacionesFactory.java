package empleado.comunicacion;

import dependencias.mensajes.empleado.SolicitudEmpleadoFactory;
import empleado.configuracion.ConfiguracionComunicacion;

public class ComunicacionOperacionesFactory {

    private static final ComunicacionOperacionesFactory INSTANCE = new ComunicacionOperacionesFactory();

    private ComunicacionOperacionesFactory() {

    }

    public static ComunicacionOperacionesFactory getInstance() {
        return INSTANCE;
    }

    public ComunicacionOperaciones crearComunicacionOperaciones(String host, ConfiguracionComunicacion configuracionComunicacion,
                                        SolicitudEmpleadoFactory solicitudEmpleadoFactory) {
        return new ComunicacionOperaciones(host, configuracionComunicacion.getPrimario(),
                configuracionComunicacion.getSecundario(), solicitudEmpleadoFactory);
    }

}
