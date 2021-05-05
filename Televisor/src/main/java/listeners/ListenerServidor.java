package listeners;

import dependencias.atencion.Atencion;
import dependencias.mensaje.Solicitud;
import vistas.LlamadosImpl;
import vistas.VistaLlamados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ListenerServidor extends Listener {

    private Logger log = Logger.getLogger("log.server.listenerTotem");
    private VistaLlamados UILlamados;

    public ListenerServidor(String host, Integer port, Integer lugares) {
        super(host, port);
        UILlamados = new LlamadosImpl(lugares);
        comunicacionServidor();
    }

    public void comunicacionServidor() {
        new Thread(() -> {
            try {
                ServerSocket s = new ServerSocket(10402);
                while (true) {
                    Socket socket = s.accept();
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    Solicitud solicitud = (Solicitud) in.readObject();
                    Atencion atencion = solicitud.getAtencion();
                    if (solicitud.getOrden().equals("ASIGNAR")) {
                        log.info("NUEVA SOLICITUD DE ASIGNACIÓN DESDE EMPLEADO");
                        UILlamados.cargarLlamado(atencion.getDNI(), solicitud.getBox(), UILlamados.getUltimaPosicionLibre());
                        out.writeObject(atencion);
                        log.info("ATENCION ASIGNADA. DNI: " + atencion.getDNI());
                    }
                    else if (solicitud.getOrden().equals("CANCELAR")){
                        log.info("NUEVA SOLICITUD DE CANCELACIÓN DESDE EMPLEADO");
                        UILlamados.quitarLlamado(0);
                        log.info("ATENCION CANCELADA. DNI: " + atencion.getDNI());
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

}
