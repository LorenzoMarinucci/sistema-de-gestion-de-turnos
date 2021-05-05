package servidor_central.espera.Queue;

import dependencias.atencion.Atencion;
import dependencias.atencion.Tipo;
import dependencias.mensajes.totem.Registro;
import dependencias.mensajes.totem.RegistroFactory;
import lombok.Synchronized;
import servidor_central.excepciones.FilaDeEsperaVaciaException;

import java.util.Map;
import java.util.PriorityQueue;

public class FilaDeEsperaPQ extends FilaDeEsperaAbstracta {

    private Map<Tipo, Integer> prioridades;

    public FilaDeEsperaPQ(Integer tamañoMaximo, Map<Tipo, Integer> prioridades) {
        super(tamañoMaximo);
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

    @Synchronized
    public Registro agregarAtencion(Integer DNI) {
        Registro registro;
        if (fila.size() == tamañoMaximo)
            registro = RegistroFactory.nuevoRegistroFallido("Ya ha sido alcanzada la capacidad máxima de la fila de servidor_central.espera");
        else if (fila.stream().anyMatch(atencionEnEspera -> DNI.equals(atencionEnEspera.getDNI()))) {
            registro = RegistroFactory.nuevoRegistroFallido("El número de DNI tiene una atención pendiente en servidor_central.espera");
        } else {
            Atencion atencion = new Atencion(DNI);
            atencion.setPrioridad(prioridades.getOrDefault(atencion.getTipo(), 0));
            fila.add(atencion);
            registro = RegistroFactory.nuevoRegistroExitoso("Registro realizado con éxito.");
        }
        return registro;
    }

    @Synchronized
    public Atencion sacarNuevaAtencion() throws FilaDeEsperaVaciaException {
        if (fila.isEmpty()) {
            throw new FilaDeEsperaVaciaException("No hay ninguna atencion en servidor_central.espera");
        }
        return fila.poll();
    }

    @Synchronized
    @Override
    public void reingresarAtencion(Atencion atencion) {
        atencion.setTipo(Tipo.REINGRESADA);
        atencion.setPrioridad(prioridades.getOrDefault(atencion.getTipo(), 0));
        fila.add(atencion);
    }

}
