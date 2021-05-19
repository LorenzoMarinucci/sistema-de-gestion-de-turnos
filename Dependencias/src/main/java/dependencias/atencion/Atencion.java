package dependencias.atencion;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Atencion implements Serializable {

    private Integer DNI;
    private Tipo tipo;
    private Integer box;
    private Estado estado;
    private Date llegada;

    public Atencion(Integer DNI) {
        this.DNI = DNI;
        this.tipo = Tipo.NUEVA;
        this.llegada = new Date();
    }
}
