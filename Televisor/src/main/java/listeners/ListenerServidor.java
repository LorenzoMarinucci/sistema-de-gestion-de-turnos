package listeners;

import configuracion.ConfiguracionComunicacion;
import dependencias.atencion.Atencion;
import dependencias.mensajes.televisor.SolicitudTelevisor;
import servicios.ServicioVisualizacion;
import vistas.LlamadosImpl;
import vistas.VistaLlamados;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ListenerServidor extends Listener {

    private Logger log = Logger.getLogger("log.server.listenerTotem");
    private ServicioVisualizacion servicioVisualizacion;

    public ListenerServidor(ConfiguracionComunicacion configuracionComunicacion, ServicioVisualizacion servicioVisualizacion) {
        super(configuracionComunicacion.getPuerto());
        this.servicioVisualizacion = servicioVisualizacion;
        servicioVisualizacion.inicializar();
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

}
