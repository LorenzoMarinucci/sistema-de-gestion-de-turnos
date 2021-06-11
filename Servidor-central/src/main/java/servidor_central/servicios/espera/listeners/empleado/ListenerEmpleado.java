package servidor_central.servicios.espera.listeners.empleado;

import dependencias.atencion.Atencion;
import dependencias.interfaces.filaDeEspera.OperacionesEmpleado;
import dependencias.interfaces.televisor.ServicioVisualizacion;
import dependencias.mensajes.empleado.SolicitudEmpleado;
import servidor_central.servicios.registro.ServicioRegistro;
import servidor_central.servicios.espera.listeners.Listener;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ListenerEmpleado extends Listener {

    private Logger log = Logger.getLogger("log.server.listenerTotem");
    private OperacionesEmpleado operacionesEmpleado;
    private ServicioVisualizacion servicioVisualizacion;
    private ServicioRegistro servicioRegistro;

    public ListenerEmpleado(OperacionesEmpleado operacionesEmpleado, Integer port, ServicioVisualizacion servicioVisualizacion,
                            ServicioRegistro servicioRegistro) {
        this.port = port;
        this.operacionesEmpleado = operacionesEmpleado;
        this.servicioVisualizacion = servicioVisualizacion;
        this.servicioRegistro = servicioRegistro;
    }

    private void comunicacionEmpleados() {
        new Thread(() -> {
            try {
                ServerSocket s = new ServerSocket(port);
                while (true) {
                    Socket socket = s.accept();
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    Object recibido = in.readObject();
                    SolicitudEmpleado solicitud = (SolicitudEmpleado) recibido;
                    Atencion atencion = procesarSolicitud(solicitud);
                    if (solicitud.getPrimario()){
                        out.writeObject(atencion);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private Atencion procesarSolicitud(SolicitudEmpleado solicitud) throws Exception {
        Atencion atencion;
        if (solicitud.getOrden().equals("ASIGNAR")) {
            log.info("NUEVA SOLICITUD DE ASIGNACIÓN DESDE EMPLEADO");
            atencion = operacionesEmpleado.solicitarAtencion(solicitud.getBox());
            solicitud.setAtencion(atencion);
            if (atencion != null) {
                log.info("ATENCION ASIGNADA. DNI: " + atencion.getCliente().getDNI() + ", BOX: " + solicitud.getBox());
                if (solicitud.getPrimario()){
                    servicioVisualizacion.mostrarAtencion(atencion);
                    servicioRegistro.emitirRegistro("ASIGNACIÓN", atencion.getCliente(), atencion.getBox());
                }
            } else {
                log.info("NO HAY ATENCIONES EN LA FILA DE ESPERA");
            }
        } else {
            atencion = solicitud.getAtencion();
            if (solicitud.getOrden().equals("FINALIZAR")) {
                log.info("NUEVA SOLICITUD DE FINALIZACIÓN DESDE EMPLEADO");
                operacionesEmpleado.finalizarAtencion(atencion);
                if (solicitud.getPrimario()) {
                    servicioRegistro.emitirRegistro("FINALIZACIÓN", atencion.getCliente(), atencion.getBox());
                }
                log.info("ATENCION FINALIZADA. DNI: " + atencion.getCliente().getDNI());
            } else {
                String operacion = null;
                if (solicitud.getOrden().equals("CANCELAR")) {
                    log.info("NUEVA SOLICITUD DE CANCELACIÓN DESDE EMPLEADO");
                    operacionesEmpleado.cancelarAtencion(atencion);
                    operacion = "CANCELACIÓN";
                    log.info("ATENCION CANCELADA. DNI: " + atencion.getCliente().getDNI());
                } else if (solicitud.getOrden().equals("ANULAR")) {
                    log.info("NUEVA SOLICITUD DE ANULACIÓN DESDE EMPLEADO");
                    operacionesEmpleado.anularAtencion(atencion);
                    operacion = "ANULACIÓN";
                    log.info("ATENCION ANULADA. DNI: " + atencion.getCliente().getDNI());
                } else if (solicitud.getOrden().equals("CONFIRMAR")) {
                    log.info("NUEVA SOLICITUD DE CONFIRMACIÓN DESDE EMPLEADO");
                    operacionesEmpleado.confirmarAtencion(atencion);
                    operacion = "CONFIRMACIÓN";
                    log.info("ATENCION CONFIRMADA. DNI: " + atencion.getCliente().getDNI());
                }
                if (solicitud.getPrimario()){
                    servicioVisualizacion.quitarAtencion(atencion);
                    servicioRegistro.emitirRegistro(operacion, atencion.getCliente(), atencion.getBox());
                }
            }
        }
        return atencion;
    }

    @Override
    public void iniciar() {
        log.info("LISTENER EMPLEADO INICIADO EN PUERTO " + this.port);
        comunicacionEmpleados();
    }
}
