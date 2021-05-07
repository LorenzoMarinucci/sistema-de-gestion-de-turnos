package servidor_central.comunicacion.TCP;

import dependencias.atencion.Atencion;
import dependencias.interfaces.televisor.ServicioVisualizacion;
import dependencias.mensajes.televisor.SolicitudTelevisor;
import dependencias.mensajes.televisor.SolicitudTelevisorFactory;
import servidor_central.configuracion.ConfiguracionComunicacionServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ComunicacionVisualizacion implements ServicioVisualizacion {

    private String host;
    private Integer port;

    public ComunicacionVisualizacion(String host, ConfiguracionComunicacionServer configuracionComunicacionServer) {
        this.host = host;
        this.port = configuracionComunicacionServer.getPuertoTelevisor();
    }

    private void enviarMensaje(SolicitudTelevisor solicitudTelevisor) {
        Socket socket = null;
        try {
            socket = new Socket(host, port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(solicitudTelevisor);
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mostrarAtencion(Atencion atencion) {
        enviarMensaje(SolicitudTelevisorFactory.nuevaSolicitudMostrar(atencion));
    }

    @Override
    public void quitarAtencion(Atencion atencion) {
        enviarMensaje(SolicitudTelevisorFactory.nuevaSolicitudQuitar(atencion));
    }

    @Override
    public void inicializar() {
        enviarMensaje(SolicitudTelevisorFactory.nuevaSolicitudInicializar());
    }
}
