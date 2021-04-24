package modelo;

import lombok.Data;

import java.util.Comparator;

@Data
public class Atencion {

    private Integer DNI;
    private Estado estado;
    private Empleado empleado;
    private Integer prioridad;

    public Atencion(Integer DNI) {
        this.DNI = DNI;
    }
}
