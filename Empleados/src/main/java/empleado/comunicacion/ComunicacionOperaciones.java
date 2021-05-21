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
    private List<Integer> portsSecundarios;

    public ComunicacionOperaciones(String host, ConfiguracionComunicacion configuracionComunicacion) {
        this.host = host;
        this.portPrimario = configuracionComunicacion.getPuertoPrimario();
        this.portsSecundarios = configuracionComunicacion.getPuertosSecundarios();
    }

    @Override
    public Atencion solicitarAtencion(Integer box) throws SolicitudException {
        return enviarMensaje(SolicitudEmpleadoFactory.nuevaSolicitudAsignacion(box));
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
    public Atencion confirmarAtencion(Atencion atencion) throws SolicitudException {
        return enviarMensaje(SolicitudEmpleadoFactory.nuevaSolicitudConfirmar(atencion));
    }

    @Override
    public void finalizarAtencion(Atencion atencion) throws SolicitudException {
        enviarMensaje(SolicitudEmpleadoFactory.nuevaSolicitudFinalizar(atencion));
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
