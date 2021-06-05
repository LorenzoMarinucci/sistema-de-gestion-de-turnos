package servidor_central.espera.queue;

import dependencias.atencion.Atencion;
import dependencias.atencion.Cliente;
import dependencias.atencion.Tipo;
import dependencias.mensajes.totem.Registro;
import dependencias.mensajes.totem.RegistroFactory;
import lombok.Synchronized;
import servidor_central.espera.FilaDeEspera;
import servidor_central.espera.criterios.Criterio;
import servidor_central.excepciones.FilaDeEsperaException;

import java.util.*;

public class FilaDeEsperaPQ implements FilaDeEspera {

    private Integer tamañoMaximo;
    private Queue<Atencion> fila;

    public FilaDeEsperaPQ(Integer tamañoMaximo, Criterio criterio) {
        this.tamañoMaximo = tamañoMaximo;
        fila = new PriorityQueue<>(criterio);
    }

    @Synchronized
    @Override
    public void agregarAtencion(Cliente cliente) throws FilaDeEsperaException {
        if (fila.size() == tamañoMaximo)
            throw new FilaDeEsperaException("Ya ha sido alcanzada la capacidad máxima de la fila de espera.");
        else if (fila.stream().anyMatch(atencionEnEspera -> cliente.getDNI().equals(atencionEnEspera.getCliente().getDNI()))) {
            throw new FilaDeEsperaException("El número de DNI tiene una atención pendiente en la fila de espera.");
        } else {
            Atencion atencion = new Atencion(cliente);
            fila.add(atencion);
        }
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

    @Override
    public void establecerFila(Iterator<Atencion> atenciones) {
        atenciones.forEachRemaining(fila::add);
    }

    @Override
    public Iterator<Atencion> obtenerFila() {
        return this.fila.iterator();
    }

}
