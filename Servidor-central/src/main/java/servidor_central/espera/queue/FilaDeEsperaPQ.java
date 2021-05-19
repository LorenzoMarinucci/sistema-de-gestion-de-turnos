package servidor_central.espera.queue;

import dependencias.atencion.Atencion;
import dependencias.atencion.Tipo;
import dependencias.mensajes.totem.Registro;
import dependencias.mensajes.totem.RegistroFactory;
import servidor_central.configuracion.ConfiguracionFilaDeEspera;
import servidor_central.espera.FilaDeEspera;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class FilaDeEsperaPQ implements FilaDeEspera {

    private Map<String, Integer> prioridades;
    private Integer tamañoMaximo;
    private Queue<Atencion> fila;

    public FilaDeEsperaPQ(ConfiguracionFilaDeEspera configuracionFilaDeEspera) {
        this.tamañoMaximo = configuracionFilaDeEspera.getTamañoFila();
        fila = new PriorityQueue<>((Atencion atencion1, Atencion atencion2) -> {
            Integer prioridad1 = prioridades.getOrDefault(atencion1.getTipo().toString(), 0);
            Integer prioridad2 = prioridades.getOrDefault(atencion2.getTipo().toString(), 0);
            System.out.println(prioridad1);
            System.out.println(prioridad2);
            if (prioridad1 > prioridad2)
                return -1;
            else if (prioridad2 > prioridad1)
                return 1;
            else if (atencion1.getLlegada().before(atencion2.getLlegada()))
                return -1;
            else
                return 1;
        });
        this.prioridades = configuracionFilaDeEspera.getPrioridades();
    }

    @Override
    public Registro agregarAtencion(Integer DNI) {
        Registro registro;
        synchronized (this.fila) {
            if (fila.size() == tamañoMaximo)
                registro = RegistroFactory.nuevoRegistroFallido("Ya ha sido alcanzada la capacidad máxima de la fila de espera.");
            else if (fila.stream().anyMatch(atencionEnEspera -> DNI.equals(atencionEnEspera.getDNI()))) {
                registro = RegistroFactory.nuevoRegistroFallido("El número de DNI tiene una atención pendiente en la fila de espera.");
            } else {
                Atencion atencion = new Atencion(DNI);
                fila.add(atencion);
                registro = RegistroFactory.nuevoRegistroExitoso("Registro realizado con éxito.");
                this.fila.notifyAll();
            }
            return registro;
        }
    }

    @Override
    public Atencion sacarNuevaAtencion() {
        synchronized (this.fila) {
            while (fila.isEmpty()) {
                try {
                    this.fila.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return fila.poll();
        }
    }

    @Override
    public void reingresarAtencion(Atencion atencion) {
        atencion.setTipo(Tipo.REINGRESADA);
        synchronized (this.fila) {
            fila.add(atencion);
            this.fila.notifyAll();
        }
    }

}
