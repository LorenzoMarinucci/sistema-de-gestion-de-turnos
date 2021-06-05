package dependencias.atencion;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Atencion implements Serializable {

    private Cliente cliente;
    private Integer box;
    private Estado estado;
    private Date llegada;
    private Tipo tipo;

    public Atencion(Cliente cliente) {
        this.cliente = cliente;
        this.llegada = new Date();
        this.tipo = Tipo.NUEVA;
    }
}
