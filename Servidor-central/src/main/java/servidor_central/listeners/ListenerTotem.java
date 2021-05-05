package servidor_central.listeners;

import dependencias.mensajes.totem.Registro;
import servidor_central.servicios.ServicioEspera;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ListenerTotem extends Listener {

    private Logger log = Logger.getLogger("log.server.listenerTotem");

    public ListenerTotem(ServicioEspera servicioEspera, Integer port) {
        super(servicioEspera, port);
        recibirDNI();
    }

    public void recibirDNI() {
        new Thread(() -> {
            try {
                ServerSocket s = new ServerSocket(port);
                while (true) {
                    Socket socket = s.accept();
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String DNI = in.readLine();
                    log.info("NUEVO MENSAJE TOTEM. DNI " + DNI);
                    Registro informeRegistro = servicioEspera.realizarRegistro(DNI);
                    out.writeObject(informeRegistro);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
