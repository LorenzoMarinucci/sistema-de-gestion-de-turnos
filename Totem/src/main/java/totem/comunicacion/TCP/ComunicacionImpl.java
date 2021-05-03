package totem.comunicacion.TCP;

import dependencias.mensaje.Registro;
import totem.comunicacion.Comunicacion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ComunicacionImpl implements Comunicacion {

    private String host;
    private Integer port;

    public ComunicacionImpl(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

	@Override
	public Registro enviarDNI(String DNI) {
        Registro informe;
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
            informe = new Registro(false, "Ha habido un fallo al establer la conexión con el servidor.");
        }
        return informe;
	}
	
}
