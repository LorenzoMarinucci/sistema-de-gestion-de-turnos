package servidor_central.espera.queue;

import dependencias.atencion.Atencion;
import dependencias.atencion.Cliente;
import dependencias.atencion.Tipo;
import dependencias.mensajes.totem.Registro;
import dependencias.mensajes.totem.RegistroFactory;
import lombok.Synchronized;
import servidor_central.espera.FilaDeEspera;
import servidor_central.espera.criterios.Criterio;

import java.util.*;

public class FilaDeEsperaPQ implements FilaDeEspera {

    private Integer tamañoMaximo;
    private Queue<Atencion> fila;
    private RegistroFactory registroFactory;

    public FilaDeEsperaPQ(Integer tamañoMaximo, RegistroFactory registroFactory, Criterio criterio) {
        this.tamañoMaximo = tamañoMaximo;
        this.registroFactory = registroFactory;
        fila = new PriorityQueue<>(criterio);
    }

    @Synchronized
    @Override
    public Registro agregarAtencion(Cliente cliente) {
        Registro registro;
        if (fila.size() == tamañoMaximo)
            registro = registroFactory.nuevoRegistroFallido("Ya ha sido alcanzada la capacidad máxima de la fila de espera.");
        else if (fila.stream().anyMatch(atencionEnEspera -> cliente.getDNI().equals(atencionEnEspera.getCliente().getDNI()))) {
            registro = registroFactory.nuevoRegistroFallido("El número de DNI tiene una atención pendiente en la fila de espera.");
        } else {
            Atencion atencion = new Atencion(cliente);
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

    @Override
    public void establecerFila(Iterator<Atencion> atenciones) {
        atenciones.forEachRemaining(fila::add);
    }

    @Override
    public Iterator<Atencion> obtenerFila() {
        return this.fila.iterator();
    }

}
