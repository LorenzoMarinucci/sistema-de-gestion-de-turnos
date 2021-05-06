package servidor_central.listeners;

import dependencias.atencion.Atencion;
import dependencias.mensajes.empleado.SolicitudEmpleado;
import servidor_central.comunicacion.ComunicacionTelevisor;
import servidor_central.servicios.ServicioEspera;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ListenerEmpleado extends Listener {

    private Logger log = Logger.getLogger("log.server.listenerTotem");
    private ComunicacionTelevisor comunicacionTelevisor;

    public ListenerEmpleado(ServicioEspera servicioEspera, ComunicacionTelevisor comunicacionTelevisor, Integer port) {
        super(servicioEspera, port);
        this.comunicacionTelevisor = comunicacionTelevisor;
        comunicacionEmpleados();
    }

    public void comunicacionEmpleados() {
        new Thread(() -> {
            try {
                ServerSocket s = new ServerSocket(port);
                Atencion atencion;
                while (true) {
                    Socket socket = s.accept();
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    SolicitudEmpleado solicitud = (SolicitudEmpleado) in.readObject();
                    if (solicitud.getOrden().equals("ASIGNAR")) {
                        log.info("NUEVA SOLICITUD DE ASIGNACIÓN DESDE EMPLEADO");
                        atencion = servicioEspera.solicitudAtencion(solicitud.getBox());
                        solicitud.setAtencion(atencion);
                        out.writeObject(atencion);
                        log.info("ATENCION ASIGNADA. DNI: " + atencion.getDNI() + ", BOX: " + solicitud.getBox());
                        comunicacionTelevisor.enviarSolicitudMostrar(atencion);
                    } else {
                        atencion = solicitud.getAtencion();
                        if (solicitud.getOrden().equals("FINALIZAR")) {
                            log.info("NUEVA SOLICITUD DE FINALIZACIÓN DESDE EMPLEADO");
                            log.info("ATENCION FINALIZADA. DNI: " + atencion.getDNI());
                        } else {
                            if (solicitud.getOrden().equals("CANCELAR")) {
                                log.info("NUEVA SOLICITUD DE CANCELACIÓN DESDE EMPLEADO");
                                servicioEspera.cancelarAtencion(atencion);
                                log.info("ATENCION CANCELADA. DNI: " + atencion.getDNI());
                            }
                            else if (solicitud.getOrden().equals("ANULAR")) {
                                log.info("NUEVA SOLICITUD DE ANULACIÓN DESDE EMPLEADO");
                                log.info("ATENCION ANULADA. DNI: " + atencion.getDNI());
                            }
                            else if (solicitud.getOrden().equals("CONFIRMAR")) {
                                log.info("NUEVA SOLICITUD DE CONFIRMACIÓN DESDE EMPLEADO");
                                log.info("ATENCION CONFIRMADA. DNI: " + atencion.getDNI());
                            }
                            comunicacionTelevisor.enviarSolicitudQuitar(atencion);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

}
