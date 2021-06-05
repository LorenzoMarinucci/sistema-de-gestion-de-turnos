package dependencias.atencion;

import lombok.Data;

@Data
public class Cliente {

    private Integer DNI;
    private String nombre;
    private Categoria categoria;

}
