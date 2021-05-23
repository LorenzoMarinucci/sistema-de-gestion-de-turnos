package servidor_central.servicios.listeners.servidor;

import dependencias.atencion.Atencion;
import dependencias.interfaces.filaDeEspera.Sincronizacion;
import dependencias.mensajes.empleado.SolicitudEmpleado;
import servidor_central.servicios.listeners.Listener;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ListenerServidor extends Listener {

    private Logger log = Logger.getLogger("log.server.listenerServidor");

    private Sincronizacion sincronizacion;

    public ListenerServidor(Integer port, Sincronizacion sincronizacion) {
        this.port = port;
        this.sincronizacion = sincronizacion;
    }

    private void procesarSincronizacion() {
        new Thread(() -> {
            try {
                ServerSocket s = new ServerSocket(port);
                while (true) {
                    Socket socket = s.accept();
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String mensaje = in.readLine();
                    if (mensaje.equals("SINCRONIZACION")) {
                        log.info("NUEVA SOLICITUD DE SINCRONIZACION");
                        List<Atencion> atenciones = new ArrayList<>();
                        sincronizacion.obtenerAtenciones().forEachRemaining(atenciones::add);
                        out.writeObject(atenciones);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void iniciar() {
        procesarSincronizacion();
    }
}
