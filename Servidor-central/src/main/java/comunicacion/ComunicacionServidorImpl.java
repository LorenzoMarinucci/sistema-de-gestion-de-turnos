package comunicacion;

import atencion.Atencion;
import controladores.Controlador;
import mensaje.Registro;
import mensaje.Solicitud;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ComunicacionServidorImpl implements ComunicacionServidor{

    private Controlador controlador;

    public ComunicacionServidorImpl(Controlador controlador) {
        this.controlador = controlador;
        this.recibirDNI();
        this.comunicacionEmpleados();
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
                    Registro informeRegistro = controlador.realizarRegistro(DNI);
                    out.writeObject(informeRegistro);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void comunicacionEmpleados() {
        new Thread(() -> {
            try {
                ServerSocket s = new ServerSocket(10400);
                while (true) {
                    Socket socket = s.accept();
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    Solicitud solicitud = (Solicitud) in.readObject();
                    if (solicitud.getSolicitud().equals("ASIGNAR")) {
                        Atencion atencion = controlador.solicitudAtencion();
                        System.out.println("Atencion asignada. DNI: " + atencion.getDNI());
                        out.writeObject(atencion);
                    }
                    else if (solicitud.getSolicitud().equals("CANCELAR")){
                        Atencion atencion = solicitud.getAtencion();
                        controlador.cancelarAtencion(atencion);
                        System.out.println("Atencion cancelada. DNI: " + atencion.getDNI());
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
