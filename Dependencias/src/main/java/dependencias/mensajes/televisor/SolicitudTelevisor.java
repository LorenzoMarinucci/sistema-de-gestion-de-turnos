package dependencias.mensajes.televisor;

import dependencias.atencion.Atencion;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SolicitudTelevisor implements Serializable {

    private String orden;
    private Atencion atencion;

}
