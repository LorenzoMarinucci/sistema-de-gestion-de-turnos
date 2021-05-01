package espera;

import atencion.Atencion;
import mensaje.Registro;

public interface FilaDeEspera {

    Registro agregarAtencion(Integer DNI) throws Exception;
    Atencion sacarNuevaAtencion() throws Exception;
    void reingresarAtencion(Atencion atencion);
    void setTamañoMaximo(Integer tamañoMaximo);

}
