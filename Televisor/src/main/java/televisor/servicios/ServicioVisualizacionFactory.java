package televisor.servicios;

import dependencias.interfaces.televisor.ServicioVisualizacion;
import televisor.configuracion.ConfiguracionTelevisor;
import televisor.vistas.JFrame.LlamadosImpl;

public class ServicioVisualizacionFactory {

    private static final ServicioVisualizacionFactory INSTANCE = new ServicioVisualizacionFactory();

    private ServicioVisualizacionFactory() {

    }

    public static ServicioVisualizacionFactory getInstance() {
        return INSTANCE;
    }

    public ServicioVisualizacionImpl crearServicioVisualizacion(ConfiguracionTelevisor configuracionTelevisor) {
        return new ServicioVisualizacionImpl(new LlamadosImpl(configuracionTelevisor.getLugares()),
                configuracionTelevisor.getLugares());
    }

}
