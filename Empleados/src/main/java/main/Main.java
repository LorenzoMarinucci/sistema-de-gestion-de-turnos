package main;

import controlador.Controlador;
import vistas.VistaEmpleadoImpl;

public class Main {

	public static void main(String[] args) {
		Controlador controlador = new Controlador(new VistaEmpleadoImpl());
	}

}
