package empleado.comunicacion.TCP;

import dependencias.atencion.Atencion;
import dependencias.mensajes.empleado.SolicitudEmpleado;
import dependencias.mensajes.empleado.SolicitudEmpleadoFactory;
import empleado.comunicacion.Comunicacion;
import empleado.excepciones.SolicitudException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ComunicacionImpl implements Comunicacion {

    private String host;
    private Integer port;

    public ComunicacionImpl(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Atencion solicitarAtencion(Integer box) throws SolicitudException {
        Atencion atencion = null;
        SolicitudEmpleado solicitud = SolicitudEmpleadoFactory.nuevaSolicitudAsignacion(box);
        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(solicitud);
            atencion = (Atencion) in.readObject();
            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
            throw new SolicitudException(e.getMessage());
        }
        return atencion;
    }

    @Override
    public void cancelarAtencion(Atencion atencion) throws SolicitudException {
        SolicitudEmpleado solicitud = SolicitudEmpleadoFactory.nuevaSolicitucCancelacion(atencion);
        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(solicitud);
            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
            throw new SolicitudException("Ha habido un fallo al establecer la conexión con el servidor");
        }
    }
}
