package empleado.sesion;

import dependencias.atencion.Atencion;
import lombok.Getter;
import lombok.Setter;

public class SesionImpl implements Sesion {

    @Getter
    private Integer numeroDeBox;

    @Setter
    @Getter
    private Atencion atencion = null;

    public SesionImpl(Integer numeroDeBox) {
        this.numeroDeBox = numeroDeBox;
    }

}
