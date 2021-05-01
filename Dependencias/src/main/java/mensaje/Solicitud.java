package mensaje;

import atencion.Atencion;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Solicitud implements Serializable {

    private String solicitud;
    private Atencion atencion;

    public Solicitud(String solicitud){
        this.solicitud = solicitud;
        this.atencion = null;
    }

}
