package utilidades;

public class ValidadorImpl implements Validador {

	public boolean DNIesValido(String DNI) {
		return !DNI.isEmpty() && DNI.charAt(0) != '0';
	}
	
}
