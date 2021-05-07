package empleado.sesion;

import dependencias.atencion.Atencion;

public interface Sesion {

    Integer getNumeroDeBox();
    Atencion getAtencion();
    void setAtencion(Atencion atencion);

}
