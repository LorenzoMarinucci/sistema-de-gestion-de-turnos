package servidor_central.espera;

import dependencias.atencion.Atencion;
import dependencias.atencion.Tipo;
import dependencias.mensaje.Registro;
import servidor_central.excepciones.DniRepetidoException;
import servidor_central.excepciones.FilaDeEsperaVaciaException;
import servidor_central.excepciones.SinCapacidadException;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class FilaDeEsperaPQ extends FilaDeEsperaAbstracta {

    private Map<Tipo, Integer> prioridades = new HashMap<>();

    public FilaDeEsperaPQ(Map<Tipo, Integer> prioridades) {
        fila = new PriorityQueue<>((Atencion atencion1, Atencion atencion2) -> {
            if (atencion1.getPrioridad() > atencion2.getPrioridad())
                return -1;
            else if (atencion1.getPrioridad() < atencion2.getPrioridad())
                return 1;
            else
                return 0;
        });
        this.prioridades = prioridades;
    }

    public Registro agregarAtencion(Integer DNI) throws SinCapacidadException, DniRepetidoException {
        Registro registro;
        if (fila.size() == tamañoMaximo)
            registro = new Registro(false, "Ya ha sido alcanzada la capacidad máxima de la fila de servidor_central.espera");
        else if (fila.stream().anyMatch(atencionEnEspera -> DNI.equals(atencionEnEspera.getDNI()))) {
            registro = new Registro(false, "El número de DNI tiene una atención pendiente en servidor_central.espera");
        } else {
            Atencion atencion = new Atencion(DNI);
            atencion.setPrioridad(prioridades.getOrDefault(atencion.getTipo(), 0));
            fila.add(atencion);
            registro = new Registro(true, "Registro realizado con éxito.");
        }
        return registro;
    }

    public Atencion sacarNuevaAtencion() throws FilaDeEsperaVaciaException {
        if (fila.isEmpty()) {
            throw new FilaDeEsperaVaciaException("No hay ninguna atencion en servidor_central.espera");
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
