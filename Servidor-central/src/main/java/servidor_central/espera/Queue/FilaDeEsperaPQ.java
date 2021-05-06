package servidor_central.espera.Queue;

import dependencias.atencion.Atencion;
import dependencias.atencion.Tipo;
import dependencias.mensajes.totem.Registro;
import dependencias.mensajes.totem.RegistroFactory;
import lombok.Synchronized;

import java.util.Map;
import java.util.PriorityQueue;

public class FilaDeEsperaPQ extends FilaDeEsperaAbstracta {

    private Map<String, Integer> prioridades;

    public FilaDeEsperaPQ(Integer tamañoMaximo, Map<String, Integer> prioridades) {
        super(tamañoMaximo);
        fila = new PriorityQueue<>((Atencion atencion1, Atencion atencion2) -> {
            Integer prioridad1 = prioridades.getOrDefault(atencion1.getTipo().toString(), 0);
            Integer prioridad2 = prioridades.getOrDefault(atencion2.getTipo().toString(), 0);
            if (prioridad1 > prioridad2)
                return -1;
            else if (prioridad1 < prioridad2)
                return 1;
            else
                return 0;
        });
        this.prioridades = prioridades;
    }

    public Registro agregarAtencion(Integer DNI) {
        Registro registro;
        synchronized (this.fila) {
            if (fila.size() == tamañoMaximo)
                registro = RegistroFactory.nuevoRegistroFallido("Ya ha sido alcanzada la capacidad máxima de la fila de servidor_central.espera");
            else if (fila.stream().anyMatch(atencionEnEspera -> DNI.equals(atencionEnEspera.getDNI()))) {
                registro = RegistroFactory.nuevoRegistroFallido("El número de DNI tiene una atención pendiente en servidor_central.espera");
            } else {
                Atencion atencion = new Atencion(DNI);
                fila.add(atencion);
                registro = RegistroFactory.nuevoRegistroExitoso("Registro realizado con éxito.");
                this.fila.notifyAll();
            }
            return registro;
        }
    }

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
