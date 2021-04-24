package servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import excepciones.LlamadoNoEncontradoException;
import vistas.LlamadosImpl;
import vistas.VistaLlamados;

public class ServicioVisualizacionImpl implements ServicioVisualizacion {
	
	private Integer llamadosMaximosTelevisor;
	private List<Llamado> llamadosEnTelevisor = new ArrayList<>();
	private List<Llamado> llamadosEnEspera = new ArrayList<>();
	private VistaLlamados UILlamados;
	
	public ServicioVisualizacionImpl(Integer llamadosTelevisor) {
		this.llamadosMaximosTelevisor = llamadosTelevisor;
	}

	@Override
	public void mostrarLlamado(String DNI, String box) {
		Llamado llamado = new Llamado(DNI, box);
		if (llamadosEnEspera.size() == llamadosMaximosTelevisor) {
			llamadosEnEspera.add(llamado);
		}
		else {
			llamadosEnTelevisor.add(llamado);
			UILlamados.cargarLlamado(DNI, box, llamadosEnTelevisor.indexOf(llamado));
		}
	}

	@Override
	public void quitarLlamado(String DNI) throws LlamadoNoEncontradoException {
		Optional<Llamado> OptionalLlamado = llamadosEnEspera.stream().filter(llamado -> llamado.getDNI().equals(DNI)).findFirst();
		if (OptionalLlamado.isPresent()) {
			llamadosEnEspera.remove(OptionalLlamado.get());
		}
		else {
			OptionalLlamado = llamadosEnTelevisor.stream().filter(llamado -> llamado.getDNI().equals(DNI)).findFirst();
			if (OptionalLlamado.isEmpty()) {
				throw new LlamadoNoEncontradoException();
			}
			UILlamados.quitarLlamado(llamadosEnTelevisor.indexOf(OptionalLlamado.get()));
			llamadosEnTelevisor.remove(OptionalLlamado.get());
		}
		
	}
	
	public void inicializarVista() {
		UILlamados = new LlamadosImpl(llamadosMaximosTelevisor);
	}

}
