package comunicacion;

import controladores.Controlador;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ComunicacionServidorImpl implements ComunicacionServidor{

    private Controlador controlador;

    public ComunicacionServidorImpl(Controlador controlador) {
        this.controlador = controlador;
        this.recibirDNI();
    }

    @Override
    public void recibirDNI() {
        new Thread(() -> {
            try {
                ServerSocket s = new ServerSocket(9999);
                while (true) {
                    Socket socket = s.accept();
                    //PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    //System.out.println("llego");
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String DNI = in.readLine();
                    in.close();
                    System.out.println(DNI);
                    InformeRegistro informeRegistro = controlador.realizarRegistro(DNI);
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    out.writeObject(informeRegistro);
                    out.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
