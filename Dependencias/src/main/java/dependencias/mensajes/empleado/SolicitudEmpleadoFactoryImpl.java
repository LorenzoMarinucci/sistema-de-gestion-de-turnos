package dependencias.mensajes.empleado;

import dependencias.atencion.Atencion;

public class SolicitudEmpleadoFactoryImpl implements SolicitudEmpleadoFactory {

    private static final SolicitudEmpleadoFactoryImpl INSTANCE = new SolicitudEmpleadoFactoryImpl();

    private SolicitudEmpleadoFactoryImpl() {}

    public static SolicitudEmpleadoFactoryImpl getInstance() {
        return INSTANCE;
    }

    public SolicitudEmpleado nuevaSolicitudAsignacion(Integer box, Boolean primario) {
        return new SolicitudEmpleado("ASIGNAR", box, null, primario);
    }

    public SolicitudEmpleado nuevaSolicitudCancelacion(Atencion atencion, Boolean primario) {
        return new SolicitudEmpleado("CANCELAR", atencion, primario);
    }

    public SolicitudEmpleado nuevaSolicitudConfirmar(Atencion atencion, Boolean primario) {
        return new SolicitudEmpleado("CONFIRMAR", atencion, primario);
    }

    public SolicitudEmpleado nuevaSolicitudFinalizar(Atencion atencion, Boolean primario) {
        return new SolicitudEmpleado("FINALIZAR", atencion, primario);
    }

    public SolicitudEmpleado nuevaSolicitudAnular(Atencion atencion, Boolean primario) {
        return new SolicitudEmpleado("ANULAR", atencion, primario);
    }

}
