package televisor.listeners;

import televisor.configuracion.ConfiguracionComunicacionTelevisor;
import dependencias.atencion.Atencion;
import dependencias.interfaces.televisor.ServicioVisualizacion;
import dependencias.mensajes.televisor.SolicitudTelevisor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ListenerVisualizacion {

    private Logger log = Logger.getLogger("log.televisor.listenerServidor");
    private Integer puerto;
    private ServicioVisualizacion servicioVisualizacion;

    public ListenerVisualizacion(Integer puerto, ServicioVisualizacion servicioVisualizacion) {
        this.puerto = puerto;
        this.servicioVisualizacion = servicioVisualizacion;
    }

    public void comunicacionServidor() {
        new Thread(() -> {
            try {
                ServerSocket s = new ServerSocket(puerto);
                while (true) {
                    Socket socket = s.accept();
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    SolicitudTelevisor solicitud = (SolicitudTelevisor) in.readObject();
                    Atencion atencion = solicitud.getAtencion();
                    if (solicitud.getOrden().equals("MOSTRAR")) {
                        log.info("NUEVA SOLICITUD DE MUESTRA. DNI: " + atencion.getDNI() + " BOX: " + atencion.getBox());
                        servicioVisualizacion.mostrarAtencion(atencion);
                    }
                    else if (solicitud.getOrden().equals("QUITAR")){
                        log.info("NUEVA SOLICITUD DE QUITADO. DNI: " + atencion.getDNI() + " BOX: " + atencion.getBox());
                        servicioVisualizacion.quitarAtencion(atencion);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void iniciar() {
        log.info("LISTENER VISUALIZACIÓN INCIALIZADO EN PUERTO " + this.puerto);
        comunicacionServidor();
    }

}
