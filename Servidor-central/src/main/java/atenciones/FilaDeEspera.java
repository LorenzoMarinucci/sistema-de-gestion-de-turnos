package atenciones;

import excepciones.SinCapacidadException;
import modelo.Atencion;

import java.util.Queue;

public abstract class FilaDeEspera {

    private Queue<Atencion> fila;

    public abstract void agregarAtencion(Integer DNI) throws Exception;
    public abstract Atencion sacarNuevaAtencion() throws Exception;

}
