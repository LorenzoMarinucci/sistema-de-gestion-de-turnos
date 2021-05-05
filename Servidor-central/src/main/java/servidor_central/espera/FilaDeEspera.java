package servidor_central.espera;

import dependencias.atencion.Atencion;
import dependencias.mensajes.totem.Registro;
import servidor_central.excepciones.FilaDeEsperaVaciaException;

public interface FilaDeEspera {

    Registro agregarAtencion(Integer DNI);
    Atencion sacarNuevaAtencion() throws FilaDeEsperaVaciaException;
    void reingresarAtencion(Atencion atencion);

}
