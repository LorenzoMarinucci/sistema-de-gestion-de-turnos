package dependencias.mensajes.empleado;

import dependencias.atencion.Atencion;

public class SolicitudEmpleadoFactory {

    public static SolicitudEmpleado nuevaSolicitudAsignacion(Integer box) {
        return new SolicitudEmpleado("ASIGNAR", box);
    }

    public static SolicitudEmpleado nuevaSolicitucCancelacion(Atencion atencion) {
        return new SolicitudEmpleado("CANCELAR", atencion);
    }

}
