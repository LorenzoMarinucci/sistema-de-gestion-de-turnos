package dependencias.mensajes.totem;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Registro implements Serializable {

    private boolean registroExitoso;
    private String mensaje;

}
