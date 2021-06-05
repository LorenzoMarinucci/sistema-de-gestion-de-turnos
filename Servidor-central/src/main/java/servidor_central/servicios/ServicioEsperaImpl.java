package servidor_central.servicios;

import dependencias.atencion.Atencion;
import dependencias.atencion.Cliente;
import dependencias.atencion.Estado;
import dependencias.interfaces.clientes.ServicioClientes;
import dependencias.interfaces.filaDeEspera.OperacionesEmpleado;
import dependencias.interfaces.filaDeEspera.RegistroTotem;
import dependencias.interfaces.filaDeEspera.Sincronizacion;
import dependencias.interfaces.monitor.Monitoreo;
import dependencias.mensajes.totem.Registro;
import servidor_central.espera.FilaDeEspera;

import java.util.Iterator;
import java.util.Optional;
import java.util.logging.Logger;

public class ServicioEsperaImpl implements RegistroTotem, OperacionesEmpleado, Sincronizacion, Monitoreo {

    private static final Logger log = Logger.getLogger("log.totem.servicioEspera");
    private FilaDeEspera filaDeEspera;
    private ServicioClientes servicioClientes;

    public ServicioEsperaImpl(FilaDeEspera filaDeEspera, ServicioClientes servicioClientes) {
        this.filaDeEspera = filaDeEspera;
        this.servicioClientes = servicioClientes;
    }

    @Override
    public Atencion solicitarAtencion(Integer box) {
        log.info("SOLICITUD DE ATENCION. BOX: " + box);
        Optional<Atencion> atencion = filaDeEspera.sacarNuevaAtencion();
        if (atencion.isPresent()) {
            atencion.get().setEstado(Estado.ASIGNADA);
            atencion.get().setBox(box);
        }
        return atencion.orElse(null);
    }

    @Override
    public void cancelarAtencion(Atencion atencion) {
        atencion.setEstado(Estado.CANCELADA);
        log.info("CANCELANDO SOLICITUD DE ATENCIÓN");
        filaDeEspera.reingresarAtencion(atencion);
    }

    @Override
    public void anularAtencion(Atencion atencion) {
        atencion.setEstado(Estado.ANULADA);
    }

    @Override
    public void finalizarAtencion(Atencion atencion) {
        atencion.setEstado(Estado.FINALIZADA);
    }

    @Override
    public Atencion confirmarAtencion(Atencion atencion) {
        atencion.setEstado(Estado.CONFIRMADA);
        return atencion;
    }

    @Override
    public Registro agregarAtencion(Integer DNI) {
        log.info("REALIZANDO NUEVO REGISTRO. DNI: " + DNI);
        Registro informeRegistro = null;
        Cliente cliente = servicioClientes.obtenerCliente(DNI);
        informeRegistro = filaDeEspera.agregarAtencion(cliente);
        informeRegistro.setCliente(cliente);
        if (informeRegistro.isRegistroExitoso()) {
            log.info("REGISTRO EXITOSO");
        } else {
            log.info("REGISTRO FALLIDO. " + informeRegistro.getMensaje());
        }
        return informeRegistro;
    }

    @Override
    public Iterator<Atencion> obtenerAtenciones() {
        return this.filaDeEspera.obtenerFila();
    }

    @Override
    public Boolean obtenerRespuesta() {
        return true;
    }
}
