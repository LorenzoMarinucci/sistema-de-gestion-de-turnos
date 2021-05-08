package televisor.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import televisor.configuracion.ConfiguracionTelevisor;
import dependencias.atencion.Atencion;
import dependencias.interfaces.televisor.ServicioVisualizacion;
import lombok.Synchronized;
import televisor.vistas.JFrame.LlamadosImpl;
import televisor.vistas.VistaLlamados;

public class ServicioVisualizacionImpl implements ServicioVisualizacion {
	
	private Integer atencionesMaximosTelevisor;
	private List<Atencion> atencionesEnTelevisor = new ArrayList<>();
	private List<Atencion> atencionesEnEspera = new ArrayList<>();
	private VistaLlamados UILlamados;
	
	public ServicioVisualizacionImpl(ConfiguracionTelevisor configuracionTelevisor) {
		this.atencionesMaximosTelevisor = configuracionTelevisor.getLugares();
	}

	@Synchronized
	@Override
	public void mostrarAtencion(Atencion atencion) {
		if (atencionesEnTelevisor.size() == atencionesMaximosTelevisor) {
			atencionesEnEspera.add(atencion);
		}
		else {
			atencionesEnTelevisor.add(atencion);
			UILlamados.cargarLlamado(atencion, atencionesEnTelevisor.indexOf(atencion));
		}
	}

	@Synchronized
	@Override
	public void quitarAtencion(Atencion atencion) {
		Optional<Atencion> OptionalAtencion = atencionesEnEspera.stream().filter(atencionFila ->
				atencionFila.getDNI().equals(atencion.getDNI())).findFirst();
		if (OptionalAtencion.isPresent()) {
			atencionesEnEspera.remove(OptionalAtencion.get());
		}
		else {
			OptionalAtencion = atencionesEnTelevisor.stream().filter(atencionTelevisor ->
					atencionTelevisor.getDNI().equals(atencion.getDNI())).findFirst();
			if (OptionalAtencion.isPresent()) {
				UILlamados.quitarLlamado(atencionesEnTelevisor.indexOf(OptionalAtencion.get()));
				atencionesEnTelevisor.remove(OptionalAtencion.get());
				if (!atencionesEnEspera.isEmpty()) {
					mostrarAtencion(atencionesEnEspera.remove(0));
				}
			}
		}
	}
	
	public void inicializar() {
		UILlamados = new LlamadosImpl(atencionesMaximosTelevisor);
	}

}
