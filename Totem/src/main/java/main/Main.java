package main;

import comunicacion.TCP.ComunicacionImpl;
import configuracion.ConfiguracionXML;
import controlador.Controlador;
import utilidades.ValidadorImpl;
import vistas.JFrame.BienvenidaImpl;
import vistas.JFrame.FinImpl;
import vistas.JFrame.RegistroImpl;

public class Main {

	public static void main(String[] args) {
		
		Controlador controlador = new Controlador(new BienvenidaImpl(), new RegistroImpl(), new FinImpl(),
				new ValidadorImpl(), new ComunicacionImpl(new ConfiguracionXML()));

	}

}
