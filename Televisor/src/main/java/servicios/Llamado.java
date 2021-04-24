package servicios;

public class Llamado {
	
	private String DNI;
	private String box;
	
	public Llamado(String DNI, String box) {
		super();
		this.DNI = DNI;
		this.box = box;
	}
	
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String DNI) {
		this.DNI = DNI;
	}
	public String getBox() {
		return box;
	}
	public void setBox(String box) {
		this.box = box;
	}
	
	
}
