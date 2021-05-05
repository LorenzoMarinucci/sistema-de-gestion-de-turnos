package servidor_central.comunicacion.TCP;

import dependencias.atencion.Atencion;
import dependencias.mensajes.televisor.SolicitudTelevisor;
import dependencias.mensajes.televisor.SolicitudTelevisorFactory;
import lombok.AllArgsConstructor;
import servidor_central.comunicacion.ComunicacionTelevisor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@AllArgsConstructor
public class ComunicacionTelevisorImpl implements ComunicacionTelevisor {

    private String host;
    private Integer port;

    @Override
    public void enviarSolicitudMostrar(Atencion atencion) {
        enviarMensaje(SolicitudTelevisorFactory.nuevaSolicitudMostrar(atencion));
    }

    @Override
    public void enviarSolicitudQuitar(Atencion atencion) {
        enviarMensaje(SolicitudTelevisorFactory.nuevaSolicitudQuitar(atencion));
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
}
