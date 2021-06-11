package servidor_central.servicios.espera.listeners.servidor;

import dependencias.atencion.Atencion;
import dependencias.interfaces.filaDeEspera.Sincronizacion;
import servidor_central.servicios.espera.listeners.Listener;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ListenerSincronizacion extends Listener {

    private Logger log = Logger.getLogger("log.server.listenerServidor");

    private Sincronizacion sincronizacion;

    public ListenerSincronizacion(Integer port, Sincronizacion sincronizacion) {
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
                        log.info("NUEVA SOLICITUD DE SINCRONIZACIÓN");
                        List<Atencion> atenciones = new ArrayList<>();
                        sincronizacion.obtenerAtenciones().forEachRemaining(atenciones::add);
                        out.writeObject(atenciones);
                    }
                    log.info("SOLICITUD DE SINCRONIZACIÓN SATISFECHA");
                }
            } catch (Exception e) {
                log.info("FALLO AL SATISFACER LA SOLICITUD DE SINCRONIZACIÓN");
            }
        }).start();
    }

    @Override
    public void iniciar() {
        log.info("LISTENER DE SINCRONIZACIÓN INICIADO EN PUERTO " + this.port);
        procesarSincronizacion();
    }
}
