package empleado.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import dependencias.atencion.Atencion;
import dependencias.interfaces.filaDeEspera.OperacionesEmpleado;
import empleado.sesion.Sesion;
import empleado.vistas.VistaEmpleado;

public class Controlador implements ActionListener {

    private final Logger log = Logger.getLogger("log.empleado.controlador");

    private VistaEmpleado vistaEmpleado;
    private Sesion sesion;
    private OperacionesEmpleado operacionesEmpleado;

    public Controlador(VistaEmpleado vistaEmpleado, OperacionesEmpleado operacionesEmpleado, Sesion sesion) {
        this.vistaEmpleado = vistaEmpleado;
        this.sesion = sesion;
        this.operacionesEmpleado = operacionesEmpleado;
        vistaEmpleado.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        Atencion atencion;
        try {
            if (command.equals("Finalizar")) {
                log.info("SOLICITUD DE FINALIZACIÓN DE ATENCIÓN");
                operacionesEmpleado.finalizarAtencion(sesion.getAtencion());
                sesion.setAtencion(null);
                vistaEmpleado.finalizarAtencion();
            } else if (command.equals("Siguiente")) {
                log.info("SOLICITUD DE SIGUIENTE ATENCIÓN");
                atencion = operacionesEmpleado.solicitarAtencion(this.sesion.getNumeroDeBox());
                sesion.setAtencion(atencion);
                vistaEmpleado.asignarAtencion(atencion);
            } else if (command.equals("Anular")) {
                log.info("SOLICITUD DE ANULACIÓN DE ATENCIÓN");
                operacionesEmpleado.anularAtencion(sesion.getAtencion());
                sesion.setAtencion(null);
                vistaEmpleado.anularAtencion();
            } else if (command.equals("Cancelar")) {
                log.info("SOLICITUD DE CANCELACIÓN DE ATENCIÓN");
                operacionesEmpleado.cancelarAtencion(sesion.getAtencion());
                sesion.setAtencion(null);
                vistaEmpleado.cancelarAtencion();
            } else if (command.equals("Confirmar")) {
                log.info("SOLICITUD DE CONFIRMACIÓN DE ATENCIÓN");
                atencion = operacionesEmpleado.confirmarAtencion(sesion.getAtencion());
                sesion.setAtencion(atencion);
                vistaEmpleado.confirmarAtencion(atencion);
            }
        } catch (Exception exception) {
            log.info(exception.getMessage());
            vistaEmpleado.informarMensaje(exception.getMessage());
        }
    }


}
