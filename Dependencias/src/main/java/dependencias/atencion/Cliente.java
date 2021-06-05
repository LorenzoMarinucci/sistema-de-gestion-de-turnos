package dependencias.atencion;

import lombok.Data;

import java.io.Serializable;

@Data
public class Cliente implements Serializable {

    private Integer DNI;
    private String nombre;
    private Categoria categoria;

}
