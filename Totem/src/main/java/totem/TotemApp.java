package totem;

import totem.comunicacion.Comunicacion;
import totem.comunicacion.TCP.ComunicacionImpl;
import totem.configuracion.ConfiguracionTotem;
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

	public static void main(String[] args) {
		try {
			ConfiguracionTotem configuracion = cargarConfiguracion("totemConfig.xml");
			Comunicacion comunicacion = new ComunicacionImpl(InetAddress.getLocalHost().getHostAddress(),
					configuracion.getPuerto());
			Controlador controlador = new Controlador(new BienvenidaImpl(), new RegistroImpl(), new FinImpl(),
					new ValidadorImpl(), comunicacion);
		} catch (UnknownHostException | JAXBException e) {
			e.printStackTrace();
		}
	}

	private static ConfiguracionTotem cargarConfiguracion(String path) throws JAXBException {
		JAXBContext jaxbContext = null;
		jaxbContext = JAXBContext.newInstance(ConfiguracionTotem.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		ConfiguracionTotem configuracionTotem = (ConfiguracionTotem) jaxbUnmarshaller.unmarshal(new File(path));
		return configuracionTotem;
	}

}
