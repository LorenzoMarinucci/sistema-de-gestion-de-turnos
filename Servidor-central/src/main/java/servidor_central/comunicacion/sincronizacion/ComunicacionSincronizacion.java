package servidor_central.comunicacion.sincronizacion;

import dependencias.atencion.Atencion;
import dependencias.interfaces.filaDeEspera.Sincronizacion;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;
import java.util.logging.Logger;

public class ComunicacionSincronizacion implements Sincronizacion {

    private Logger log = Logger.getLogger("log.server.sincronizacion");

    private String host;
    private Integer port;

    private final Integer TIEMPO_ESPERA = 5000;

    public ComunicacionSincronizacion(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Iterator<Atencion> obtenerAtenciones() {
        Iterator<Atencion> iterator = null;
        log.info("INICIANDO SINCRONIZACIÓN");
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), TIEMPO_ESPERA);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.println("SINCRONIZACION");
            List<Atencion> atenciones = (List<Atencion>) in.readObject();
            iterator = atenciones.iterator();
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            log.info("SINCRONIZACIÓN FALLIDA");
            iterator = Collections.emptyIterator();
        }
        return iterator;
    }
}
