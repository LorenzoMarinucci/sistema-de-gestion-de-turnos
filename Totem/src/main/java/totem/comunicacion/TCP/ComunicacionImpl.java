package totem.comunicacion.TCP;

import dependencias.mensajes.totem.Registro;
import dependencias.mensajes.totem.RegistroFactory;
import totem.comunicacion.Comunicacion;
import totem.configuracion.ConfiguracionTotem;

import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ComunicacionImpl implements Comunicacion {

    private String host;
    private Integer port;

    public ComunicacionImpl(String host, ConfiguracionTotem configuracionTotem) {
        this.host = host;
        this.port = configuracionTotem.getPuerto();
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
            informe = RegistroFactory.nuevoRegistroFallido("Ha habido un fallo al establer la conexión con el servidor.");
        }
        return informe;
	}
	
}
