package servidor_central.espera.queue;

import dependencias.atencion.Atencion;
import dependencias.atencion.Tipo;
import dependencias.mensajes.totem.Registro;
import dependencias.mensajes.totem.RegistroFactory;
import lombok.Synchronized;
import servidor_central.configuracion.ConfiguracionFilaDeEspera;
import servidor_central.espera.FilaDeEspera;

import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

public class FilaDeEsperaPQ implements FilaDeEspera {

    private Map<String, Integer> prioridades;
    private Integer tamañoMaximo;
    private Queue<Atencion> fila;
    private RegistroFactory registroFactory;

    public FilaDeEsperaPQ(Integer tamañoMaximo, Map<String, Integer> prioridades, RegistroFactory registroFactory) {
        this.tamañoMaximo = tamañoMaximo;
        this.registroFactory = registroFactory;
        fila = new PriorityQueue<>((Atencion atencion1, Atencion atencion2) -> {
            Integer prioridad1 = prioridades.getOrDefault(atencion1.getTipo().toString(), 0);
            Integer prioridad2 = prioridades.getOrDefault(atencion2.getTipo().toString(), 0);
            if (prioridad1 > prioridad2)
                return -1;
            else if (prioridad2 > prioridad1)
                return 1;
            else if (atencion1.getLlegada().before(atencion2.getLlegada()))
                return -1;
            else
                return 1;
        });
        this.prioridades = prioridades;
    }

    @Synchronized
    @Override
    public Registro agregarAtencion(Integer DNI) {
        Registro registro;
        if (fila.size() == tamañoMaximo)
            registro = registroFactory.nuevoRegistroFallido("Ya ha sido alcanzada la capacidad máxima de la fila de espera.");
        else if (fila.stream().anyMatch(atencionEnEspera -> DNI.equals(atencionEnEspera.getDNI()))) {
            registro = registroFactory.nuevoRegistroFallido("El número de DNI tiene una atención pendiente en la fila de espera.");
        } else {
            Atencion atencion = new Atencion(DNI);
            fila.add(atencion);
            registro = registroFactory.nuevoRegistroExitoso("Registro realizado con éxito.");
        }
        return registro;
    }

    @Synchronized
    @Override
    public Optional<Atencion> sacarNuevaAtencion() {
        return Optional.ofNullable(fila.poll());
    }

    @Synchronized
    @Override
    public void reingresarAtencion(Atencion atencion) {
        atencion.setTipo(Tipo.REINGRESADA);
        fila.add(atencion);
    }

}
