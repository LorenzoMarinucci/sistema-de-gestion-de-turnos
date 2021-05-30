package servidor_central.servicios.listeners.monitor;

import dependencias.interfaces.monitor.Monitoreo;
import servidor_central.servicios.listeners.Listener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ListenerMonitor extends Listener {

    private Logger log = Logger.getLogger("log.server.listenerMonitoreo");

    private Monitoreo monitoreo;

    public ListenerMonitor(Integer port, Monitoreo monitoreo) {
        this.port = port;
        this.monitoreo = monitoreo;
    }

    private void recibirMensajes() {
        new Thread(() -> {
            try {
                ServerSocket s = new ServerSocket(port);
                while (true) {
                    Socket socket = s.accept();
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String mensaje = in.readLine();
                    if (mensaje.equals("PING")) {
                        log.info("NUEVA SOLICITUD DE MONITOREO");
                        Boolean respuesta = monitoreo.obtenerRespuesta();
                        if (respuesta) {
                            out.println("ACK");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void iniciar() {
        log.info("LISTENER MONITOR INICIADO EN PUERTO " + port);
        recibirMensajes();
    }
}
