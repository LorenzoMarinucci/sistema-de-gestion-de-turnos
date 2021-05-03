package servidor_central.comunicacion;

import servidor_central.controladores.Controlador;
import dependencias.atencion.Atencion;
import dependencias.mensaje.Registro;
import dependencias.mensaje.Solicitud;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ComunicacionTotemImpl implements ComunicacionTotem {

    private Controlador controlador;

    public ComunicacionTotemImpl(Controlador controlador) {
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
                    if (solicitud.getOrden().equals("ASIGNAR")) {
                        Atencion atencion = controlador.solicitudAtencion();
                        System.out.println("Atencion asignada. DNI: " + atencion.getDNI());
                        out.writeObject(atencion);
                    }
                    else if (solicitud.getOrden().equals("CANCELAR")){
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
