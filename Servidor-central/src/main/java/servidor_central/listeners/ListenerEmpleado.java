package servidor_central.listeners;

import dependencias.atencion.Atencion;
import dependencias.mensaje.Registro;
import dependencias.mensaje.Solicitud;
import servidor_central.servicios.ServicioEspera;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ListenerEmpleado extends Listener {

    private Logger log = Logger.getLogger("log.server.listenerTotem");

    public ListenerEmpleado(ServicioEspera servicioEspera, String host, Integer port) {
        super(servicioEspera, host, port);
        comunicacionEmpleados();
    }

    public void comunicacionEmpleados() {
        new Thread(() -> {
            try {
                ServerSocket s = new ServerSocket(10400);
                while (true) {
                    Socket socket = s.accept();
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    Solicitud solicitud = (Solicitud) in.readObject();
                    if (solicitud.getOrden().equals("ASIGNAR")) {
                        log.info("NUEVA SOLICITUD DE ASIGNACIÓN DESDE EMPLEADO");
                        Atencion atencion = servicioEspera.solicitudAtencion();
                        solicitud.setAtencion(atencion);
                        out.writeObject(atencion);
                        comunicacionTelevisor(solicitud);
                        log.info("ATENCION ASIGNADA. DNI: " + atencion.getDNI() + ", BOX: " + solicitud.getBox());
                    }
                    else if (solicitud.getOrden().equals("CANCELAR")) {
                        log.info("NUEVA SOLICITUD DE CANCELACIÓN DESDE EMPLEADO");
                        Atencion atencion = solicitud.getAtencion();
                        servicioEspera.cancelarAtencion(atencion);
                        comunicacionTelevisor(solicitud);
                        log.info("ATENCION CANCELADA. DNI: " + atencion.getDNI());
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void comunicacionTelevisor(Solicitud solicitud) {
        Socket socket = null;
        try {
            socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 10402);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(solicitud);
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
