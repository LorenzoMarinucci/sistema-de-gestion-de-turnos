package servidor_central.servicios;

import dependencias.atencion.Atencion;
import dependencias.mensajes.totem.Registro;

public interface ServicioEspera {

    Registro realizarRegistro(String DNI);
    Atencion solicitudAtencion(Integer box);
    void cancelarAtencion(Atencion atencion);

}
