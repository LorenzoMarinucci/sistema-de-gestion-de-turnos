package empleado.sesion;

import empleado.configuracion.ConfiguracionSesion;

public class SesionFactory {

    private static final SesionFactory INSTANCE = new SesionFactory();

    private SesionFactory() {

    }

    public static SesionFactory getInstance() {
        return INSTANCE;
    }

    public Sesion crearSesion(ConfiguracionSesion configuracionSesion) {
        return new SesionImpl(configuracionSesion.getBox());
    }

}
