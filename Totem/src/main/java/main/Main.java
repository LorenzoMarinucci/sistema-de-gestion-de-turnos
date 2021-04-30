package main;

import comunicacion.TCP.ComunicacionImpl;
import comunicacion.configuracion.ConfiguracionXML;
import controlador.Controlador;
import lombok.SneakyThrows;
import utilidades.ValidadorImpl;
import vistas.JFrame.BienvenidaImpl;
import vistas.JFrame.FinImpl;
import vistas.JFrame.RegistroImpl;

import java.net.InetAddress;

public class Main {

	@SneakyThrows
	public static void main(String[] args) {
		Controlador controlador = new Controlador(new BienvenidaImpl(), new RegistroImpl(), new FinImpl(),
				new ValidadorImpl(), new ComunicacionImpl(new ConfiguracionXML()));
	}

}
