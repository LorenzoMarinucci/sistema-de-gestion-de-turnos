package empleado;

import empleado.comunicacion.TCP.ComunicacionImpl;
import empleado.configuracion.XML.ConfiguracionComunicacionImpl;
import empleado.configuracion.XML.ConfiguracionSesionImpl;
import empleado.controlador.Controlador;
import empleado.sesion.SesionImpl;
import empleado.vistas.JFrame.VistaEmpleadoImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class EmpleadoApp {

	private static final String COMUNICACION_PATH = "comunicacionConfig.xml";
	private static final String SESION_PATH = "sesionConfig.xml";

	public static void main(String[] args) {
		try {
			ConfiguracionComunicacionImpl configuracionComunicacionEmpleado = cargarConfiguracionComunicacion();
			ConfiguracionSesionImpl configuracionSesionEmpleado = cargarConfiguracionSesion();
			Controlador controlador = new Controlador(new VistaEmpleadoImpl(),
					new ComunicacionImpl(InetAddress.getLocalHost().getHostAddress(),
							configuracionComunicacionEmpleado),
					new SesionImpl(configuracionSesionEmpleado));
		} catch (JAXBException | UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private static ConfiguracionComunicacionImpl cargarConfiguracionComunicacion() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(ConfiguracionComunicacionImpl.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		ConfiguracionComunicacionImpl configuracionComunicacion = (ConfiguracionComunicacionImpl)
				jaxbUnmarshaller.unmarshal(new File(COMUNICACION_PATH));
		return configuracionComunicacion;
	}

	private static ConfiguracionSesionImpl cargarConfiguracionSesion() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(ConfiguracionSesionImpl.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		ConfiguracionSesionImpl configuracionSesion = (ConfiguracionSesionImpl)
				jaxbUnmarshaller.unmarshal(new File(SESION_PATH));
		return configuracionSesion;
	}
}
