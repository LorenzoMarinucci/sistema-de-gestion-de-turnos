package comunicacion.TCP;

import comunicacion.Comunicacion;
import comunicacion.configuracion.ConfiguracionComunicacion;
import mensaje.Registro;

import java.io.ObjectInputStream;
import java.io.PrintWriter;
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
	public Registro enviarDNI(String DNI) {
        Registro informe = null;
		try {
            Socket socket = new Socket(host, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.println(DNI);
            informe = (Registro) in.readObject();
            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return informe;
	}
	
}
