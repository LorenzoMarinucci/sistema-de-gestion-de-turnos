package atenciones;

import excepciones.DniRepetidoException;
import excepciones.FilaDeEsperaVaciaException;
import excepciones.SinCapacidadException;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class FilaDeEsperaPQ extends FilaDeEsperaAbstracta {

    private Map<Tipo, Integer> prioridades = new HashMap<>();

    public FilaDeEsperaPQ(Map<Tipo, Integer> prioridades) {
        fila = new PriorityQueue<>((Atencion atencion1, Atencion atencion2) -> {
            if (atencion1.getPrioridad() > atencion2.getPrioridad())
                return 1;
            else if (atencion1.getPrioridad() < atencion2.getPrioridad())
                return -1;
            else
                return 0;
        });
        this.prioridades = prioridades;
    }

    public void agregarAtencion(Integer DNI) throws SinCapacidadException, DniRepetidoException {
        if (fila.size() == tamañoMaximo)
            throw new SinCapacidadException("Ya ha sido alcanzada la capacidad máxima de la fila de espera");
        if (fila.stream().anyMatch(atencionEnEspera -> DNI.equals(atencionEnEspera.getDNI())))
            throw new DniRepetidoException("El número de DNI tiene una atención pendiente en espera");
        Atencion atencion = new Atencion(DNI);
        atencion.setPrioridad(prioridades.getOrDefault(atencion.getTipo(), 0));
        fila.add(atencion);
    }

    public Atencion sacarNuevaAtencion() throws FilaDeEsperaVaciaException {
        if (fila.isEmpty()) {
            throw new FilaDeEsperaVaciaException("No hay ninguna atencion en espera");
        }
        return fila.poll();
    }

    @Override
    public void reingresarAtencion(Atencion atencion) {
        atencion.setTipo(Tipo.REINGRESADA);
        atencion.setPrioridad(prioridades.getOrDefault(atencion.getTipo(), 0));
        fila.add(atencion);
    }

}
