package comunicacion;

import atencion.Atencion;

public interface Comunicacion {

    Atencion solicitarAtencion();
    void cancelarAtencion(Atencion atencion);

}
