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
                ServerSocket s = new ServerSocket(10451);
                while (true) {
                    Socket socket = s.accept();
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String DNI = in.readLine();
                    System.out.println(DNI);
                    InformeRegistro informeRegistro = controlador.realizarRegistro(DNI);
                    out.writeObject(informeRegistro);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
