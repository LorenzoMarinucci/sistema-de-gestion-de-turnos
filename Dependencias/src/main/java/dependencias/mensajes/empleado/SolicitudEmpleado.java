package dependencias.mensajes.empleado;

import dependencias.atencion.Atencion;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SolicitudEmpleado implements Serializable {

    private String orden;
    private Integer box;
    private Atencion atencion;
    private Boolean primario;

    public SolicitudEmpleado(String orden, Integer box, Boolean primario){
        this.orden = orden;
        this.box = box;
        this.primario = primario;
    }

    public SolicitudEmpleado(String orden, Atencion atencion, Boolean primario) {
        this.orden = orden;
        this.atencion = atencion;
        this.primario = primario;
    }

    public SolicitudEmpleado(String orden, Atencion atencion) {
        this.orden = orden;
        this.atencion = atencion;
    }

}
