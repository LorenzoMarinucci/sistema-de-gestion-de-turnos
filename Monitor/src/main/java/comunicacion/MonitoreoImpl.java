package comunicacion;

import dependencias.interfaces.monitor.Monitoreo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.logging.Logger;

public class MonitoreoImpl implements Monitoreo {

    private Integer tiempoEspera;
    private String host;
    private List<Integer> puertos;

    private Logger log = Logger.getLogger("log.monitor.Monitoreo");

    public MonitoreoImpl(String host, List<Integer> puertos, Integer tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
        this.host = host;
        this.puertos = puertos;
    }

    @Override
    public Boolean obtenerRespuesta() {
        Long start = System.currentTimeMillis();
        Boolean respuesta = null;
        for (Integer puerto : puertos) {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(host, puerto), tiempoEspera);
                socket.setSoTimeout(tiempoEspera);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.println("PING");
                String mensaje = in.readLine();
                if (mensaje.equals("ACK")) {
                    Long end = System.currentTimeMillis();
                    log.info("PING EXITOSO CON EL PUERTO " + puerto + ". TIEMPO DE RESPUESTA " + (end - start) + " ms");
                    if (respuesta == null) {
                        respuesta = true;
                    }
                } else {
                    respuesta = false;
                }
                in.close();
                out.close();
                socket.close();
            } catch (Exception e) {
                log.info("FALLO AL ESTABLECER LA CONEXIÓN CON EL PUERTO " + puerto);
                respuesta = false;
            }
        }
        return respuesta;
    }

}
