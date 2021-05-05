package dependencias.mensaje;

import dependencias.atencion.Atencion;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Solicitud implements Serializable {

    private String orden;
    private Integer box;
    private Atencion atencion;

    public Solicitud(String orden, Integer box){
        this.orden = orden;
        this.box = box;
        this.atencion = null;
    }

    public Solicitud(String orden, Atencion atencion){
        this.orden = orden;
        this.atencion = atencion;
    }

}
