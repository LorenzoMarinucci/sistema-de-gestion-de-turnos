package dependencias.mensajes.empleado;

import dependencias.atencion.Atencion;

public interface SolicitudEmpleadoFactory {

    public SolicitudEmpleado nuevaSolicitudAsignacion(Integer box, Boolean actualizarTelevisor);

    public SolicitudEmpleado nuevaSolicitudCancelacion(Atencion atencion, Boolean actualizarTelevisor);

    public SolicitudEmpleado nuevaSolicitudConfirmar(Atencion atencion, Boolean actualizarTelevisor);

    public SolicitudEmpleado nuevaSolicitudFinalizar(Atencion atencion);

    public SolicitudEmpleado nuevaSolicitudAnular(Atencion atencion, Boolean actualizarTelevisor);

}
