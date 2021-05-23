package empleado.comunicacion;

import dependencias.atencion.Atencion;
import dependencias.interfaces.filaDeEspera.OperacionesEmpleado;
import dependencias.mensajes.empleado.SolicitudEmpleado;
import dependencias.mensajes.empleado.SolicitudEmpleadoFactory;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Logger;

public class ComunicacionOperaciones implements OperacionesEmpleado {

    private final Logger log = Logger.getLogger("log.empleado.comunicacion");

    private String host;
    private Integer portPrimario;
    private Integer portSecundario;
    private SolicitudEmpleadoFactory solicitudEmpleadoFactory;

    private final Integer TIEMPO_ESPERA = 1000;

    public ComunicacionOperaciones(String host, Integer portPrimario, Integer portSecundario,
                                   SolicitudEmpleadoFactory solicitudEmpleadoFactory) {
        this.host = host;
        this.portPrimario = portPrimario;
        this.portSecundario = portSecundario;
        this.solicitudEmpleadoFactory = solicitudEmpleadoFactory;
    }

    @Override
    public Atencion solicitarAtencion(Integer box) throws SolicitudException {
        Atencion atencion;
        try {
            log.info("SOLICITANDO ATENCIÓN AL SERVIDOR PRIMARIO");
            atencion = enviarMensaje(solicitudEmpleadoFactory.nuevaSolicitudAsignacion(box,true), portPrimario);
            try {
                log.info("SINCRONIZANDO SERVIDOR SECUNDARIO");
                enviarMensaje(solicitudEmpleadoFactory.nuevaSolicitudAsignacion(box,false), portSecundario);
            } catch (Exception e) {
                log.info("FALLO EN EL SERVIDOR SECUNDARIO");
            }
        } catch (Exception e) {
            log.info("FALLO EN EL SERVIDOR PRIMARIO");
            Integer aux = portPrimario;
            portPrimario = portSecundario;
            portSecundario = aux;
            log.info("SOLICITANDO ATENCIÓN AL SERVIDOR SECUNDARIO");
            atencion = enviarMensaje(solicitudEmpleadoFactory.nuevaSolicitudAsignacion(box,true), portPrimario);
        }
        return atencion;
    }

    @Override
    public void cancelarAtencion(Atencion atencion) throws SolicitudException {
        try {
            log.info("SOLICITUD DE CANCELACIÓN AL SERVIDOR PRIMARIO");
            enviarMensaje(solicitudEmpleadoFactory.nuevaSolicitudCancelacion(atencion,true), portPrimario);
            try {
                log.info("SINCRONIZANDO SERVIDOR SECUNDARIO");
                enviarMensaje(solicitudEmpleadoFactory.nuevaSolicitudCancelacion(atencion,false), portSecundario);
            } catch (Exception e) {
                log.info("FALLO EN EL SERVIDOR SECUNDARIO");
            }
        } catch (Exception e) {
            log.info("FALLO EN EL SERVIDOR PRIMARIO");
            Integer aux = portPrimario;
            portPrimario = portSecundario;
            portSecundario = aux;
            log.info("SOLICITUD DE CANCELACIÓN AL SERVIDOR SECUNDARIO");
            enviarMensaje(solicitudEmpleadoFactory.nuevaSolicitudCancelacion(atencion,true), portPrimario);
        }
    }

    @Override
    public void anularAtencion(Atencion atencion) throws SolicitudException {
        try {
            log.info("SOLICITUD DE ANULACIÓN AL SERVIDOR PRIMARIO");
            enviarMensaje(solicitudEmpleadoFactory.nuevaSolicitudAnular(atencion,true), portPrimario);
            try {
                log.info("SINCRONIZANDO SERVIDOR SECUNDARIO");
                enviarMensaje(solicitudEmpleadoFactory.nuevaSolicitudAnular(atencion,false), portSecundario);
            } catch (Exception e) {
                log.info("FALLO EN EL SERVIDOR SECUNDARIO");
            }
        } catch (Exception e) {
            log.info("FALLO EN EL SERVIDOR PRIMARIO");
            Integer aux = portPrimario;
            portPrimario = portSecundario;
            portSecundario = aux;
            log.info("SOLICITUD DE ANULACIÓN AL SERVIDOR SECUNDARIO");
            enviarMensaje(solicitudEmpleadoFactory.nuevaSolicitudAnular(atencion,true), portPrimario);
        }
    }

    @Override
    public Atencion confirmarAtencion(Atencion atencion) throws SolicitudException {
        Atencion a;
        try {
            log.info("SOLICITUD DE CONFIRMACIÓN AL SERVIDOR PRIMARIO");
            a = enviarMensaje(solicitudEmpleadoFactory.nuevaSolicitudConfirmar(atencion,true), portPrimario);
            try {
                log.info("SINCRONIZANDO SERVIDOR SECUNDARIO");
                enviarMensaje(solicitudEmpleadoFactory.nuevaSolicitudConfirmar(atencion,false), portSecundario);
            } catch (Exception e) {
                log.info("FALLO EN EL SERVIDOR SECUNDARIO");
            }
        } catch (Exception e) {
            log.info("FALLO EN EL SERVIDOR PRIMARIO");
            Integer aux = portPrimario;
            portPrimario = portSecundario;
            portSecundario = aux;
            log.info("SOLICITUD DE CONFIRMACIÓN AL SERVIDOR SECUNDARIO");
            a = enviarMensaje(solicitudEmpleadoFactory.nuevaSolicitudConfirmar(atencion,true), portPrimario);
        }
        return a;
    }

    @Override
    public void finalizarAtencion(Atencion atencion) throws SolicitudException {
        try {
            log.info("SOLICITUD DE FINALIZACIÓN AL SERVIDOR PRIMARIO");
            enviarMensaje(solicitudEmpleadoFactory.nuevaSolicitudFinalizar(atencion,true), portPrimario);
            try {
                log.info("SINCRONIZANDO SERVIDOR SECUNDARIO");
                enviarMensaje(solicitudEmpleadoFactory.nuevaSolicitudFinalizar(atencion,false), portSecundario);
            } catch (Exception e) {
                log.info("FALLO EN EL SERVIDOR SECUNDARIO");
            }
        } catch (Exception e) {
            log.info("FALLO EN EL SERVIDOR PRIMARIO");
            Integer aux = portPrimario;
            portPrimario = portSecundario;
            portSecundario = aux;
            log.info("SOLICITUD DE FINALIZACIÓN AL SERVIDOR SECUNDARIO");
            enviarMensaje(solicitudEmpleadoFactory.nuevaSolicitudFinalizar(atencion,true), portPrimario);
        }
    }

    private Atencion enviarMensaje(SolicitudEmpleado solicitud, Integer port) throws SolicitudException{
        Atencion atencion = null;
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host,port),TIEMPO_ESPERA);
            socket.setSoTimeout(TIEMPO_ESPERA);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(solicitud);
            if (solicitud.getPrimario()){
                atencion = (Atencion) in.readObject();
            }
            out.close();
            in.close();
            socket.close();
            return atencion;
        } catch (Exception e) {
            throw new SolicitudException("Ha habido un fallo al establecer la conexión con el servidor");
        }
    }
}
