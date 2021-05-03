package servidor_central.espera;

import dependencias.atencion.Atencion;
import dependencias.mensaje.Registro;

public interface FilaDeEspera {

    Registro agregarAtencion(Integer DNI) throws Exception;
    Atencion sacarNuevaAtencion() throws Exception;
    void reingresarAtencion(Atencion atencion);
    void setTamañoMaximo(Integer tamañoMaximo);

}
