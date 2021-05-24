package comunicacion;

import dependencias.interfaces.monitor.Monitoreo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Logger;

public class MonitoreoImpl implements Monitoreo {

    private Integer tiempoEspera;

    private Logger log = Logger.getLogger("log.monitor.Monitoreo");

    public MonitoreoImpl(Integer tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    @Override
    public Boolean obtenerRespuesta(String host, Integer port) {
        Long start = System.currentTimeMillis();
        Boolean respuesta = false;
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), tiempoEspera);
            socket.setSoTimeout(tiempoEspera);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("PING");
            String mensaje = in.readLine();
            if (mensaje.equals("ACK")) {
                Long end = System.currentTimeMillis();
                log.info("PING EXITOSO CON EL PUERTO " + port + ". TIEMPO DE RESPUESTA " + (end - start) + " ms");
                respuesta = true;
            }
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            log.info("FALLO AL ESTABLECER LA CONEXIÓN CON EL PUERTO " + port);
        }
        return respuesta;
    }

}
