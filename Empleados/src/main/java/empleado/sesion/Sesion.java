package empleado.sesion;

import dependencias.atencion.Atencion;

public interface Sesion {

    Integer getNumeroDeBox();
    Atencion asignarAtencion(Atencion atencion);
    Atencion cancelarAtencion();
    Atencion finalizarAtencion();
    Atencion anularAtencion();
    Atencion confirmarAtencion();

}
