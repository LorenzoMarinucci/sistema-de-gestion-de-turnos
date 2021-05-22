package totem;

import dependencias.interfaces.filaDeEspera.RegistroTotem;
import dependencias.mensajes.totem.RegistroFactory;
import dependencias.mensajes.totem.RegistroFactoryImpl;
import totem.comunicacion.ComunicacionRegistro;
import totem.comunicacion.ComunicacionRegistroFactory;
import totem.configuracion.ConfiguracionTotem;
import totem.configuracion.XML.ConfiguracionTotemImpl;
import totem.controlador.Controlador;
import totem.utilidades.ValidadorImpl;
import totem.vistas.JFrame.BienvenidaImpl;
import totem.vistas.JFrame.FinImpl;
import totem.vistas.JFrame.RegistroImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class TotemApp {

	private static final String CONFIG_PATH = "totemConfig.xml";

	public static void main(String[] args) {
		try {
			ConfiguracionTotem configuracion = cargarConfiguracion();
			RegistroTotem registroTotem = ComunicacionRegistroFactory.getInstance().crearComunicacionRegistro(
					InetAddress.getLocalHost().getHostAddress(), RegistroFactoryImpl.getInstance(), configuracion);
			Controlador controlador = new Controlador(new BienvenidaImpl(), new RegistroImpl(), new FinImpl(),
					new ValidadorImpl(), registroTotem);
		} catch (UnknownHostException | JAXBException e) {
			e.printStackTrace();
		}
	}

	private static ConfiguracionTotemImpl cargarConfiguracion() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(ConfiguracionTotemImpl.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		ConfiguracionTotemImpl configuracionTotem = (ConfiguracionTotemImpl) jaxbUnmarshaller.unmarshal(new File(CONFIG_PATH));
		return configuracionTotem;
	}

}
