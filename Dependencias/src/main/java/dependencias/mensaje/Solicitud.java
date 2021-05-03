package dependencias.mensaje;

import dependencias.atencion.Atencion;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Solicitud implements Serializable {

    private String orden;
    private Atencion atencion;

    public Solicitud(String orden){
        this.orden = orden;
        this.atencion = null;
    }

}
