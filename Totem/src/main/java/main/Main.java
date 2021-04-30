package main;

import comunicacion.ComunicacionImpl;
import configuracion.ConfiguracionXML;
import controlador.Controlador;
import utilidades.ValidadorImpl;
import vistas.BienvenidaImpl;
import vistas.FinImpl;
import vistas.RegistroImpl;

public class Main {

	public static void main(String[] args) {
		
		Controlador controlador = new Controlador(new BienvenidaImpl(), new RegistroImpl(), new FinImpl(),
				new ValidadorImpl(), new ComunicacionImpl(new ConfiguracionXML()));

	}

}
