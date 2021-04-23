package modelo;

import lombok.Data;

@Data
public class Empleado {

    private Long DNI;
    private Integer box;
    private Atencion atencion;

}
