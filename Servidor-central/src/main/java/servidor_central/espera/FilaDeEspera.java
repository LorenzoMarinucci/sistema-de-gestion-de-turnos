package servidor_central.espera;

import dependencias.atencion.Atencion;
import dependencias.mensajes.totem.Registro;

public interface FilaDeEspera {

    Registro agregarAtencion(Integer DNI);
    Atencion sacarNuevaAtencion();
    void reingresarAtencion(Atencion atencion);

}
