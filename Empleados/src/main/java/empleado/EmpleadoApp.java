package empleado;

import empleado.comunicacion.TCP.ComunicacionImpl;
import empleado.configuracion.ConfiguracionComunicacion;
import empleado.configuracion.ConfiguracionSesion;
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
			ConfiguracionComunicacion configuracionComunicacionEmpleado = cargarConfiguracionComunicacion();
			ConfiguracionSesion configuracionSesionEmpleado = cargarConfiguracionSesion();
			Controlador controlador = new Controlador(new VistaEmpleadoImpl(),
					new ComunicacionImpl(InetAddress.getLocalHost().getHostAddress(),
							configuracionComunicacionEmpleado.getPuerto()),
					new SesionImpl(configuracionSesionEmpleado.getBox()));
		} catch (JAXBException | UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private static ConfiguracionComunicacion cargarConfiguracionComunicacion() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(ConfiguracionComunicacion.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		ConfiguracionComunicacion configuracionComunicacion = (ConfiguracionComunicacion)
				jaxbUnmarshaller.unmarshal(new File(COMUNICACION_PATH));
		return configuracionComunicacion;
	}

	private static ConfiguracionSesion cargarConfiguracionSesion() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(ConfiguracionSesion.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		ConfiguracionSesion configuracionSesion = (ConfiguracionSesion)
				jaxbUnmarshaller.unmarshal(new File(SESION_PATH));
		return configuracionSesion;
	}
}
