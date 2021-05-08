package servidor_central.servicios.listeners;

import dependencias.interfaces.filaDeEspera.RegistroTotem;
import dependencias.mensajes.totem.Registro;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ListenerTotem extends Listener {

    private Logger log = Logger.getLogger("log.server.listenerTotem");
    private RegistroTotem registroTotem;

    public ListenerTotem(RegistroTotem registroTotem, Integer port) {
        super(port);
        this.registroTotem = registroTotem;
    }

    private void recibirDNI() {
        new Thread(() -> {
            try {
                ServerSocket s = new ServerSocket(port);
                while (true) {
                    Socket socket = s.accept();
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String DNI = in.readLine();
                    log.info("NUEVO MENSAJE TOTEM. DNI " + DNI);
                    Registro informeRegistro = registroTotem.agregarAtencion(Integer.parseInt(DNI));
                    out.writeObject(informeRegistro);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void iniciar() {
        recibirDNI();
    }
}
