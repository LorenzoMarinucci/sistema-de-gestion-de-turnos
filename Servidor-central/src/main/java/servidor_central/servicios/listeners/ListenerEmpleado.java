package servidor_central.servicios.listeners;

import dependencias.atencion.Atencion;
import dependencias.interfaces.filaDeEspera.OperacionesEmpleado;
import dependencias.mensajes.empleado.SolicitudEmpleado;
import servidor_central.configuracion.ConfiguracionComunicacionServer;
import servidor_central.estado.EstadoServidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ListenerEmpleado extends Listener {

    private Logger log = Logger.getLogger("log.server.listenerTotem");
    private OperacionesEmpleado operacionesEmpleado;

    public ListenerEmpleado(OperacionesEmpleado operacionesEmpleado, ConfiguracionComunicacionServer configuracionComunicacionServer, EstadoServidor estadoServidor) {
        this.portPrimario = configuracionComunicacionServer.getPuertoEmpleado();
        this.operacionesEmpleado = operacionesEmpleado;
        this.estadoServidor = estadoServidor;
    }

    private void comunicacionEmpleados() {
        new Thread(() -> {
            try {
                ServerSocket s = new ServerSocket(portPrimario);
                while (true) {
                    Socket socket = s.accept();
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    Object recibido = in.readObject();
                    try {
                        SolicitudEmpleado solicitud = (SolicitudEmpleado) recibido;
                        Atencion atencion = procesarSolicitud(solicitud);
                        out.writeObject(atencion);
                    } catch (ClassCastException e) {
                        String mensaje = (String) recibido;
                        if (mensaje.equals("SET PRIMARIO")) {
                            cambiarRol(true);
                        }
                        else if (mensaje.equals("SET SECUNDARRIO")) {
                            cambiarRol(false);
                        }
                        else if (mensaje.equals("IS PRIMARIO")) {
                            out.writeObject(estadoServidor.isPrimario());
                        }
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
            log.info("ATENCION ASIGNADA. DNI: " + atencion.getDNI() + ", BOX: " + solicitud.getBox());
        } else {
            atencion = solicitud.getAtencion();
            if (solicitud.getOrden().equals("FINALIZAR")) {
                log.info("NUEVA SOLICITUD DE FINALIZACIÓN DESDE EMPLEADO");
                operacionesEmpleado.finalizarAtencion(atencion);
                log.info("ATENCION FINALIZADA. DNI: " + atencion.getDNI());
            } else {
                if (solicitud.getOrden().equals("CANCELAR")) {
                    log.info("NUEVA SOLICITUD DE CANCELACIÓN DESDE EMPLEADO");
                    operacionesEmpleado.cancelarAtencion(atencion);
                    log.info("ATENCION CANCELADA. DNI: " + atencion.getDNI());
                } else if (solicitud.getOrden().equals("ANULAR")) {
                    log.info("NUEVA SOLICITUD DE ANULACIÓN DESDE EMPLEADO");
                    operacionesEmpleado.anularAtencion(atencion);
                    log.info("ATENCION ANULADA. DNI: " + atencion.getDNI());
                } else if (solicitud.getOrden().equals("CONFIRMAR")) {
                    log.info("NUEVA SOLICITUD DE CONFIRMACIÓN DESDE EMPLEADO");
                    operacionesEmpleado.confirmarAtencion(atencion);
                    log.info("ATENCION CONFIRMADA. DNI: " + atencion.getDNI());
                }
            }
        }
        return atencion;
    }

    @Override
    public void iniciar() {
        comunicacionEmpleados();
    }
}
