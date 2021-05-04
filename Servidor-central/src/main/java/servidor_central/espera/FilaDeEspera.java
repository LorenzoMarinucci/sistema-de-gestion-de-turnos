package servidor_central.espera;

import dependencias.atencion.Atencion;
import dependencias.mensaje.Registro;
import servidor_central.excepciones.DniRepetidoException;
import servidor_central.excepciones.FilaDeEsperaVaciaException;
import servidor_central.excepciones.SinCapacidadException;

public interface FilaDeEspera {

    Registro agregarAtencion(Integer DNI);
    Atencion sacarNuevaAtencion() throws FilaDeEsperaVaciaException;
    void reingresarAtencion(Atencion atencion);

}
