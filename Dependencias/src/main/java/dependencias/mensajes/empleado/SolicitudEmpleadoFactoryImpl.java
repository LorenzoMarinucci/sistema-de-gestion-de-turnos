package dependencias.mensajes.empleado;

import dependencias.atencion.Atencion;

public class SolicitudEmpleadoFactoryImpl implements SolicitudEmpleadoFactory {

    private static final SolicitudEmpleadoFactoryImpl INSTANCE = new SolicitudEmpleadoFactoryImpl();

    private SolicitudEmpleadoFactoryImpl() {}

    public static SolicitudEmpleadoFactoryImpl getInstance() {
        return INSTANCE;
    }

    public SolicitudEmpleado nuevaSolicitudAsignacion(Integer box, Boolean actualizarTelevisor) {
        return new SolicitudEmpleado("ASIGNAR", box, null, actualizarTelevisor);
    }

    public SolicitudEmpleado nuevaSolicitudCancelacion(Atencion atencion, Boolean actualizarTelevisor) {
        return new SolicitudEmpleado("CANCELAR", atencion, actualizarTelevisor);
    }

    public SolicitudEmpleado nuevaSolicitudConfirmar(Atencion atencion, Boolean actualizarTelevisor) {
        return new SolicitudEmpleado("CONFIRMAR", atencion, actualizarTelevisor);
    }

    public SolicitudEmpleado nuevaSolicitudFinalizar(Atencion atencion) {
        return new SolicitudEmpleado("FINALIZAR", atencion);
    }

    public SolicitudEmpleado nuevaSolicitudAnular(Atencion atencion, Boolean actualizarTelevisor) {
        return new SolicitudEmpleado("ANULAR", atencion, actualizarTelevisor);
    }

}
