package servidor_central.servicios;

import dependencias.atencion.Atencion;
import dependencias.atencion.Estado;
import dependencias.interfaces.filaDeEspera.OperacionesEmpleado;
import dependencias.interfaces.filaDeEspera.RegistroTotem;
import dependencias.interfaces.televisor.ServicioVisualizacion;
import dependencias.mensajes.totem.Registro;
import lombok.Getter;
import lombok.Setter;
import servidor_central.espera.FilaDeEspera;

import java.util.logging.Logger;

public class ServicioEsperaImpl implements RegistroTotem, OperacionesEmpleado {

    @Getter
    @Setter
    private Boolean actualizarVisualizacion;

    private static final Logger log = Logger.getLogger("log.totem.servicioEspera");
    private ServicioVisualizacion servicioVisualizacion;
    private FilaDeEspera filaDeEspera;

    public ServicioEsperaImpl(FilaDeEspera filaDeEspera, ServicioVisualizacion servicioVisualizacion, Boolean actualizarVisualizacion) {
        this.filaDeEspera = filaDeEspera;
        this.servicioVisualizacion = servicioVisualizacion;
        this.actualizarVisualizacion = actualizarVisualizacion;
    }

    @Override
    public Atencion solicitarAtencion(Integer box) {
        log.info("SOLICITUD DE ATENCION. BOX: " + box);
        Atencion atencion = null;
        atencion = filaDeEspera.sacarNuevaAtencion();
        atencion.setEstado(Estado.ASIGNADA);
        atencion.setBox(box);
        if (actualizarVisualizacion)
            servicioVisualizacion.mostrarAtencion(atencion);
        return atencion;
    }

    @Override
    public void cancelarAtencion(Atencion atencion) {
        atencion.setEstado(Estado.CANCELADA);
        log.info("CANCELANDO SOLICITUD DE ATENCIÓN");
        filaDeEspera.reingresarAtencion(atencion);
        if (actualizarVisualizacion)
            servicioVisualizacion.quitarAtencion(atencion);
    }

    @Override
    public void anularAtencion(Atencion atencion) {
        atencion.setEstado(Estado.ANULADA);
        if (actualizarVisualizacion)
            servicioVisualizacion.quitarAtencion(atencion);
    }

    @Override
    public void finalizarAtencion(Atencion atencion) {
        atencion.setEstado(Estado.FINALIZADA);
    }

    @Override
    public Atencion confirmarAtencion(Atencion atencion) {
        atencion.setEstado(Estado.CONFIRMADA);
        if (actualizarVisualizacion)
            servicioVisualizacion.quitarAtencion(atencion);
        return atencion;
    }

    @Override
    public Registro agregarAtencion(Integer DNI) {
        log.info("REALIZANDO NUEVO REGISTRO. DNI: " + DNI);
        Registro informeRegistro = null;
        informeRegistro = filaDeEspera.agregarAtencion(DNI);
        if (informeRegistro.isRegistroExitoso()) {
            log.info("REGISTRO EXITOSO");
        } else {
            log.info("REGISTRO FALLIDO. " + informeRegistro.getMensaje());
        }
        return informeRegistro;
    }
}
