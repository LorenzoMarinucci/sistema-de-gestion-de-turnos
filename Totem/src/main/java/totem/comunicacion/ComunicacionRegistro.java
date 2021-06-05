package totem.comunicacion;

import dependencias.interfaces.filaDeEspera.RegistroTotem;
import dependencias.mensajes.empleado.SolicitudEmpleadoFactory;
import dependencias.mensajes.totem.Registro;
import dependencias.mensajes.totem.RegistroFactory;
import dependencias.mensajes.totem.SolicitudTotem;
import dependencias.mensajes.totem.SolicitudTotemFactory;
import totem.configuracion.ConfiguracionTotem;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class ComunicacionRegistro implements RegistroTotem {

    private final Logger log = Logger.getLogger("log.totem.comunicacion");

    private String host;
    private Integer portPrimario;
    private Integer portSecundario;
    private RegistroFactory registroFactory;
    private SolicitudTotemFactory solicitudTotemFactory;

    private final Integer TIEMPO_ESPERA = 1000;

    public ComunicacionRegistro(String host, Integer portPrimario,
                                Integer portSecundario, RegistroFactory registroFactory, SolicitudTotemFactory solicitudTotemFactory) {
        this.host = host;
        this.portPrimario = portPrimario;
        this.portSecundario = portSecundario;
        this.registroFactory = registroFactory;
        this.solicitudTotemFactory = solicitudTotemFactory;
    }

    @Override
    public Registro agregarAtencion(Integer DNI) {
        Registro informe = null;
        try {
            log.info("ENVIANDO DNI AL SERVIDOR PRIMARIO");
            informe = enviarDNI(DNI,portPrimario,true);
            try {
                log.info("SINCRONIZANDO SERVIDOR SECUNDARIO");
                enviarDNI(DNI,portSecundario,false);
            } catch (Exception e) {
                log.info("FALLO EN EL SERVIDOR SECUNDARIO");
            }
        } catch (Exception e) {
            log.info("FALLO EN EL SERVIDOR PRIMARIO");
            Integer aux = this.portPrimario;
            this.portPrimario = this.portSecundario;
            this.portSecundario = aux;
            log.info("ENVIANDO DNI AL SERVIDOR SECUNDARIO");
            try {
                informe = enviarDNI(DNI,portPrimario,true);
            } catch (Exception exception) {
                informe = registroFactory.nuevoRegistroFallido("Ha habido un fallo al establer la conexión con el servidor.", null);
            }
        }
        return informe;
    }

    private Registro enviarDNI(Integer DNI, Integer port, Boolean primario) throws IOException, ClassNotFoundException {
        Registro informe = null;
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(host,port),TIEMPO_ESPERA);
        socket.setSoTimeout(TIEMPO_ESPERA);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        out.writeObject(solicitudTotemFactory.solicitudRegistro(DNI,primario));
        if (primario) {
            informe = (Registro) in.readObject();
        }
        out.close();
        in.close();
        socket.close();
        return informe;
    }
}
