package modelo;

import lombok.Data;

import java.util.Comparator;

@Data
public class Atencion {

    private Long DNI;
    private Estado estado;
    private Empleado empleado;
    private Integer prioridad;
}
