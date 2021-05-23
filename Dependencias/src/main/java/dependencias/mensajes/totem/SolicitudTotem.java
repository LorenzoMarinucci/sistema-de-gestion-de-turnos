package dependencias.mensajes.totem;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
public class SolicitudTotem implements Serializable {

    @Getter
    private Integer DNI;

    @Getter
    private Boolean primario;

}
