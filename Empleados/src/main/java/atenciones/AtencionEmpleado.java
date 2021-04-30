package atenciones;

public class AtencionEmpleado {
	
	private Integer DNI;
	private Estado estado;
	
	public AtencionEmpleado(Integer DNI) {
		this.DNI = DNI;
	}

	public Integer getDNI() {
		return DNI;
	}

	public void setDNI(Integer dNI) {
		DNI = dNI;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
