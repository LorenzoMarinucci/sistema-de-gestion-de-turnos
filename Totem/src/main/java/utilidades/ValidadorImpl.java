package utilidades;

public class Validador {

	public static boolean DNIesValido(String DNI) {
		return !DNI.isEmpty() && DNI.charAt(0) != '0';
	}
	
}
