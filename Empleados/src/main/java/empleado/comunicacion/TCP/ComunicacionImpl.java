package empleado.comunicacion.TCP;

import dependencias.atencion.Atencion;
import dependencias.mensajes.empleado.SolicitudEmpleado;
import dependencias.mensajes.empleado.SolicitudEmpleadoFactory;
import empleado.comunicacion.Comunicacion;
import empleado.configuracion.ConfiguracionComunicacion;
import empleado.excepciones.SolicitudException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ComunicacionImpl implements Comunicacion {

    private String host;
    private Integer port;

    public ComunicacionImpl(String host, ConfiguracionComunicacion configuracionComunicacion) {
        this.host = host;
        this.port = configuracionComunicacion.getPuerto();
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
        enviarMensaje(SolicitudEmpleadoFactory.nuevaSolicitudCancelacion(atencion));
    }

    @Override
    public void anularAtencion(Atencion atencion) throws SolicitudException {
        enviarMensaje(SolicitudEmpleadoFactory.nuevaSolicitudAnular(atencion));
    }

    @Override
    public void confirmarAtencion(Atencion atencion) throws SolicitudException {
        enviarMensaje(SolicitudEmpleadoFactory.nuevaSolicitudConfirmar(atencion));
    }

    @Override
    public void finalizarAtencion(Atencion atencion) throws SolicitudException {
        enviarMensaje(SolicitudEmpleadoFactory.nuevaSolicitudFinalizar(atencion));
    }

    private void enviarMensaje(SolicitudEmpleado solicitud) throws SolicitudException{
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
