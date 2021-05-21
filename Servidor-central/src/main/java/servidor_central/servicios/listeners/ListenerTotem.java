package servidor_central.servicios.listeners;

import dependencias.atencion.Atencion;
import dependencias.interfaces.filaDeEspera.RegistroTotem;
import dependencias.mensajes.empleado.SolicitudEmpleado;
import dependencias.mensajes.totem.Registro;
import servidor_central.configuracion.ConfiguracionComunicacionServer;
import servidor_central.estado.EstadoServidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ListenerTotem extends Listener {

    private Logger log = Logger.getLogger("log.server.listenerTotem");
    private RegistroTotem registroTotem;

    public ListenerTotem(RegistroTotem registroTotem, ConfiguracionComunicacionServer configuracionComunicacionServer, EstadoServidor estadoServidor) {
        this.portPrimario = configuracionComunicacionServer.getPuertoTotem();
        this.registroTotem = registroTotem;
        this.estadoServidor = estadoServidor;
    }

    private void recibirDNI() {
        new Thread(() -> {
            try {
                ServerSocket s = new ServerSocket(portPrimario);
                while (true) {
                    Socket socket = s.accept();
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String recibido = in.readLine();
                    try {
                        Integer DNI = Integer.parseInt(recibido);
                        log.info("NUEVO MENSAJE TOTEM. DNI " + DNI);
                        Registro informeRegistro = registroTotem.agregarAtencion(DNI);
                        out.writeObject(informeRegistro);
                    } catch (NumberFormatException e) {
                        String mensaje = (String) recibido;
                        if (mensaje.equals("SET PRIMARIO")) {
                            cambiarRol(true);
                        }
                        else if (mensaje.equals("SET SECUNDARRIO")) {
                            cambiarRol(false);
                        }
                        else if (mensaje.equals("IS PRIMARIO")) {
                            out.writeObject(estadoServidor.isPrimario());
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
        recibirDNI();
    }
}
