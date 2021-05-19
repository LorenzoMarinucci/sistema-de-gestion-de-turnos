package dependencias.atencion;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Atencion implements Serializable {

    private Integer DNI;
    private Tipo tipo;
    private Integer box;
    private Estado estado;
    private LocalDate llegada;

    public Atencion(Integer DNI) {
        this.DNI = DNI;
        this.tipo = Tipo.NUEVA;
        this.llegada = LocalDate.now();
    }
}
