package comunicacion.TCP;

import comunicacion.Comunicacion;
import comunicacion.InformeRegistro;
import configuracion.ConfiguracionComunicacion;

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
	public InformeRegistro enviarDNI(String dni) {
        InformeRegistro informe = null;
		try {
            Socket socket = new Socket(host, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            System.out.println(dni);
            out.println(dni);
            out.close();
            informe = (InformeRegistro) in.readObject();
            in.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return informe;
	}
	
}
