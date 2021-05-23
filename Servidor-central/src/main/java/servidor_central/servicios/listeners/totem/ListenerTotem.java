package servidor_central.servicios.listeners.totem;

import dependencias.interfaces.filaDeEspera.RegistroTotem;
import dependencias.mensajes.totem.Registro;
import dependencias.mensajes.totem.SolicitudTotem;
import servidor_central.servicios.listeners.Listener;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ListenerTotem extends Listener {

    private Logger log = Logger.getLogger("log.server.listenerTotem");
    private RegistroTotem registroTotem;

    public ListenerTotem(RegistroTotem registroTotem, Integer port) {
        this.port = port;
        this.registroTotem = registroTotem;
    }

    private void recibirDNI() {
        new Thread(() -> {
            try {
                ServerSocket s = new ServerSocket(port);
                while (true) {
                    Socket socket = s.accept();
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    SolicitudTotem solicitud = (SolicitudTotem) in.readObject();
                    log.info("NUEVO MENSAJE TOTEM. DNI " + solicitud.getDNI());
                    Registro informeRegistro = registroTotem.agregarAtencion(solicitud.getDNI());
                    if (solicitud.getPrimario()){
                        out.writeObject(informeRegistro);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void iniciar() {
        log.info("LISTENER TOTEM INICIADO EN PUERTO " + this.port);
        recibirDNI();
    }
}
