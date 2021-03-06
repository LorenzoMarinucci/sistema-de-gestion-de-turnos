package servidor_central.comunicacion.visualizacion;

import dependencias.atencion.Atencion;
import dependencias.interfaces.televisor.ServicioVisualizacion;
import dependencias.mensajes.televisor.SolicitudTelevisor;
import dependencias.mensajes.televisor.SolicitudTelevisorFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class ComunicacionVisualizacion implements ServicioVisualizacion {

    private Logger log = Logger.getLogger("log.server.visualizacion");

    private String host;
    private Integer port;
    private SolicitudTelevisorFactory solicitudTelevisorFactory;

    public ComunicacionVisualizacion(String host, Integer port, SolicitudTelevisorFactory solicitudTelevisorFactory) {
        this.host = host;
        this.port = port;
        this.solicitudTelevisorFactory = solicitudTelevisorFactory;
    }

    private void enviarMensaje(SolicitudTelevisor solicitudTelevisor) {
        Socket socket = null;
        try {
            socket = new Socket(host, port);
            log.info("REALIZANDO SOLICITUD AL TELEVISOR");
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(solicitudTelevisor);
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            log.info("FALLO AL REALIZAR LA SOLICITUD AL TELEVISOR");
        }
    }

    @Override
    public void mostrarAtencion(Atencion atencion) {
        enviarMensaje(solicitudTelevisorFactory.nuevaSolicitudMostrar(atencion));
    }

    @Override
    public void quitarAtencion(Atencion atencion) {
        enviarMensaje(solicitudTelevisorFactory.nuevaSolicitudQuitar(atencion));
    }

}
