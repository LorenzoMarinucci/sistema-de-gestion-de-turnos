package dependencias.mensajes.empleado;

import dependencias.atencion.Atencion;

public interface SolicitudEmpleadoFactory {

    public SolicitudEmpleado nuevaSolicitudAsignacion(Integer box, Boolean primario);

    public SolicitudEmpleado nuevaSolicitudCancelacion(Atencion atencion, Boolean primario);

    public SolicitudEmpleado nuevaSolicitudConfirmar(Atencion atencion, Boolean primario);

    public SolicitudEmpleado nuevaSolicitudFinalizar(Atencion atencion, Boolean primario);

    public SolicitudEmpleado nuevaSolicitudAnular(Atencion atencion, Boolean primario);

}
