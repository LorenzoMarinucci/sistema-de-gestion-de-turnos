package dependencias.mensajes.empleado;

import dependencias.atencion.Atencion;

public class SolicitudEmpleadoFactory {

    public static SolicitudEmpleado nuevaSolicitudAsignacion(Integer box) {
        return new SolicitudEmpleado("ASIGNAR", box);
    }

    public static SolicitudEmpleado nuevaSolicitudCancelacion(Atencion atencion) {
        return new SolicitudEmpleado("CANCELAR", atencion);
    }

    public static SolicitudEmpleado nuevaSolicitudConfirmar(Atencion atencion) {
        return new SolicitudEmpleado("CONFIRMAR", atencion);
    }

    public static SolicitudEmpleado nuevaSolicitudFinalizar(Atencion atencion) {
        return new SolicitudEmpleado("FINALIZAR", atencion);
    }

    public static SolicitudEmpleado nuevaSolicitudAnular(Atencion atencion) {
        return new SolicitudEmpleado("ANULAR", atencion);
    }

}
