package comunicacion;

import atencion.Atencion;
import comunicacion.configuracion.ConfiguracionComunicacion;
import mensaje.Solicitud;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ComunicacionImpl implements Comunicacion {

    private String host;
    private Integer port;

    public ComunicacionImpl(ConfiguracionComunicacion configuracion) {
        try {
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        port = configuracion.getPuerto();
    }

    @Override
    public Atencion solicitarAtencion() {
        Atencion atencion = null;
        Solicitud solicitud;
        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            solicitud = new Solicitud("ASIGNAR");
            out.writeObject(solicitud);
            atencion = (Atencion) in.readObject();
            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return atencion;
    }

    @Override
    public void cancelarAtencion(Atencion atencion) {
        Solicitud solicitud;
        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            solicitud = new Solicitud("CANCELAR", atencion);
            out.writeObject(solicitud);
            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
