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
                while (true) {
                    Socket socket = s.accept();
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    SolicitudEmpleado solicitud = (SolicitudEmpleado) in.readObject();
                    if (solicitud.getOrden().equals("ASIGNAR")) {
                        log.info("NUEVA SOLICITUD DE ASIGNACIÓN DESDE EMPLEADO");
                        Atencion atencion = servicioEspera.solicitudAtencion(solicitud.getBox());
                        solicitud.setAtencion(atencion);
                        out.writeObject(atencion);
                        log.info("ATENCION ASIGNADA. DNI: " + atencion.getDNI() + ", BOX: " + solicitud.getBox());
                        comunicacionTelevisor.enviarSolicitudMostrar(atencion);
                    }
                    else if (solicitud.getOrden().equals("CANCELAR")) {
                        log.info("NUEVA SOLICITUD DE CANCELACIÓN DESDE EMPLEADO");
                        Atencion atencion = solicitud.getAtencion();
                        servicioEspera.cancelarAtencion(atencion);
                        log.info("ATENCION CANCELADA. DNI: " + atencion.getDNI());
                        comunicacionTelevisor.enviarSolicitudQuitar(atencion);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

}
