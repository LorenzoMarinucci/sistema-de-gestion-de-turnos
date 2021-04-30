package comunicacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class InformeRegistro implements Serializable {

	private boolean registroExitoso;

	private String mensaje;
}
