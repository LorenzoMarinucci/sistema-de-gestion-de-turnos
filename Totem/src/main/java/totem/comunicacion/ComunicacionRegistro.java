package totem.comunicacion;

import dependencias.interfaces.filaDeEspera.RegistroTotem;
import dependencias.mensajes.totem.Registro;
import dependencias.mensajes.totem.RegistroFactory;
import totem.configuracion.ConfiguracionTotem;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Optional;

public class ComunicacionRegistro implements RegistroTotem {

    private String host;
    private Integer portPrimario;
    private List<Integer> portsSecundarios;

    public ComunicacionRegistro(String host, ConfiguracionTotem configuracionTotem) {
        this.host = host;
        this.portPrimario = configuracionTotem.getPuertoPrimario();
        this.portsSecundarios = configuracionTotem.getPuertosSecundarios();
    }

    @Override
    public Registro agregarAtencion(Integer DNI) {
        Registro informe = null;
        try {
            establecerPrimario();
            informe = enviarDNI(DNI);
        } catch (Exception e) {
            informe = RegistroFactory.nuevoRegistroFallido("Ha habido un fallo al establer la conexión con el servidor.");
        }
        return informe;
    }

    private void establecerPrimario() {
        Boolean respuesta;
        try {
            Socket socket = new Socket(host, portPrimario);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.println("IS PRIMARIO");
            respuesta = in.readBoolean();
            if (!respuesta) {
                socket.close();
                for (Integer secundario: portsSecundarios) {
                    socket = new Socket(host, secundario);
                    out = new PrintWriter(socket.getOutputStream(), true);
                    in = new ObjectInputStream(socket.getInputStream());
                    out.println("IS PRIMARIO");
                    respuesta = in.readBoolean();
                    if (respuesta) {
                        out.println("SET PRIMARIO");
                        portsSecundarios.add(portPrimario);
                        portPrimario = secundario;
                        socket.close();
                        return;
                    }
                    socket.close();
                }
                socket = new Socket(host, portsSecundarios.get(0));
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println("SET PRIMARIO");
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Registro enviarDNI(Integer DNI) throws IOException, ClassNotFoundException {
        Registro informe;
        Socket socket = new Socket(host, portPrimario);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        out.println(DNI);
        informe = (Registro) in.readObject();
        out.close();
        in.close();
        socket.close();
        return informe;
    }
}
