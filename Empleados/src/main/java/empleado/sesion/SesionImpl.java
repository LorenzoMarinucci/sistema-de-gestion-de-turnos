package empleado.sesion;

import dependencias.atencion.Atencion;
import dependencias.atencion.Estado;
import empleado.configuracion.ConfiguracionSesion;

public class SesionImpl implements Sesion {

    private Integer numeroDeBox;
    private Atencion atencion = null;

    public SesionImpl(ConfiguracionSesion configuracionSesion) {
        this.numeroDeBox = configuracionSesion.getBox();
    }

    @Override
    public Integer getNumeroDeBox() {
        return this.numeroDeBox;
    }

    @Override
    public Atencion asignarAtencion(Atencion atencion) {
        atencion.setEstado(Estado.ASIGNADA);
        this.atencion = atencion;
        return atencion;
    }

    @Override
    public Atencion cancelarAtencion() {
        Atencion atencionCancelada = this.atencion;
        atencionCancelada.setEstado(Estado.CANCELADA);
        this.atencion = null;
        return atencionCancelada;
    }

    @Override
    public Atencion finalizarAtencion() {
        Atencion atencionFinalizada = this.atencion;
        atencionFinalizada.setEstado(Estado.FINALIZADA);
        this.atencion = null;
        return atencionFinalizada;
    }

    @Override
    public Atencion anularAtencion() {
        Atencion atencionAnulada = this.atencion;
        atencionAnulada.setEstado(Estado.ANULADA);
        this.atencion = null;
        return atencionAnulada;
    }

    @Override
    public Atencion confirmarAtencion() {
        Atencion atencionConfirmada = this.atencion;
        atencionConfirmada.setEstado(Estado.CONFIRMADA);
        return atencionConfirmada;
    }
}
