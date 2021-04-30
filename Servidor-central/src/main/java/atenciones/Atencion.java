package atenciones;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;

@Getter
@Setter
public class Atencion {

    private Integer DNI;
    private Tipo tipo;
    private Integer prioridad;

    public Atencion(Integer DNI) {
        this.DNI = DNI;
        this.tipo = Tipo.NUEVA;
    }
}
