package dependencias.atencion;

import lombok.Data;

import java.io.Serializable;

@Data
public class Atencion implements Serializable {

    private Integer DNI;
    private Tipo tipo;
    private Integer prioridad;
    private Integer box;
    private Estado estado;

    public Atencion(Integer DNI) {
        this.DNI = DNI;
        this.tipo = Tipo.NUEVA;
    }
}
