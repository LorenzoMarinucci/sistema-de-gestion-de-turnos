package empleado.comunicacion;

import dependencias.atencion.Atencion;
import dependencias.interfaces.filaDeEspera.OperacionesEmpleado;
import dependencias.mensajes.empleado.SolicitudEmpleado;
import dependencias.mensajes.empleado.SolicitudEmpleadoFactory;
import empleado.configuracion.ConfiguracionComunicacion;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ComunicacionOperaciones implements OperacionesEmpleado {

    private String host;
    private Integer portPrimario;
    private Integer portSecundario;
    private SolicitudEmpleadoFactory solicitudEmpleadoFactory;

    public ComunicacionOperaciones(String host, Integer portPrimario, Integer portSecundario,
                                   SolicitudEmpleadoFactory solicitudEmpleadoFactory) {
        this.host = host;
        this.portPrimario = portPrimario;
        this.portSecundario = portSecundario;
        this.solicitudEmpleadoFactory = solicitudEmpleadoFactory;
    }

    @Override
    public Atencion solicitarAtencion(Integer box) throws SolicitudException {
        return enviarMensaje(solicitudEmpleadoFactory.nuevaSolicitudAsignacion(box));
    }

    @Override
    public void cancelarAtencion(Atencion atencion) throws SolicitudException {
        enviarMensaje(solicitudEmpleadoFactory.nuevaSolicitudCancelacion(atencion));
    }

    @Override
    public void anularAtencion(Atencion atencion) throws SolicitudException {
        enviarMensaje(solicitudEmpleadoFactory.nuevaSolicitudAnular(atencion));
    }

    @Override
    public Atencion confirmarAtencion(Atencion atencion) throws SolicitudException {
        return enviarMensaje(solicitudEmpleadoFactory.nuevaSolicitudConfirmar(atencion));
    }

    @Override
    public void finalizarAtencion(Atencion atencion) throws SolicitudException {
        enviarMensaje(solicitudEmpleadoFactory.nuevaSolicitudFinalizar(atencion));
    }

    private Atencion enviarMensaje(SolicitudEmpleado solicitud) throws SolicitudException{
        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(solicitud);
            Atencion atencion = (Atencion) in.readObject();
            out.close();
            in.close();
            socket.close();
            return atencion;
        } catch (Exception e) {
            throw new SolicitudException("Ha habido un fallo al establecer la conexión con el servidor");
        }
    }
}
