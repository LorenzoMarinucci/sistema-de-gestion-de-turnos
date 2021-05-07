package empleado.sesion;

import dependencias.atencion.Atencion;
import empleado.configuracion.ConfiguracionSesion;
import lombok.Getter;
import lombok.Setter;

public class SesionImpl implements Sesion {

    @Getter
    private Integer numeroDeBox;

    @Setter
    @Getter
    private Atencion atencion = null;

    public SesionImpl(ConfiguracionSesion configuracionSesion) {
        this.numeroDeBox = configuracionSesion.getBox();
    }

}
