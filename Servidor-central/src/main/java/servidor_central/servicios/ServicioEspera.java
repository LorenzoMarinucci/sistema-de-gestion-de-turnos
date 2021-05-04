package servidor_central.servicios;

import dependencias.atencion.Atencion;
import dependencias.mensaje.Registro;

public interface ServicioEspera {

    Registro realizarRegistro(String DNI);
    Atencion solicitudAtencion();
    void cancelarAtencion(Atencion atencion);

}
