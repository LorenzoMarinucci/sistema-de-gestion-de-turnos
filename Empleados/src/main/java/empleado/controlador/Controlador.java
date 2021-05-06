package empleado.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import dependencias.atencion.Atencion;
import empleado.comunicacion.Comunicacion;
import empleado.excepciones.SolicitudException;
import empleado.sesion.Sesion;
import empleado.vistas.VistaEmpleado;

public class Controlador implements ActionListener {

	private final Logger log = Logger.getLogger("log.empleado.controlador");

    private VistaEmpleado vistaEmpleado;
    private Sesion sesion;
    private Comunicacion comunicacion;

    public Controlador(VistaEmpleado vistaEmpleado, Comunicacion comunicacion, Sesion sesion) {
        this.vistaEmpleado = vistaEmpleado;
        this.sesion = sesion;
        this.comunicacion = comunicacion;
        vistaEmpleado.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        Atencion atencion;
        try {
            if (command.equals("Finalizar")) {
            	log.info("SOLICITUD DE FINALIZACIÓN DE ATENCIÓN");
                atencion = sesion.finalizarAtencion();
                comunicacion.finalizarAtencion(atencion);
                vistaEmpleado.finalizarAtencion();
            } else if (command.equals("Siguiente")) {
            	log.info("SOLICITUD DE SIGUIENTE ATENCIÓN");
                atencion = comunicacion.solicitarAtencion(sesion.getNumeroDeBox());
                sesion.asignarAtencion(atencion);
                vistaEmpleado.asignarAtencion(atencion);
            } else if (command.equals("Anular")) {
            	log.info("SOLICITUD DE ANULACIÓN DE ATENCIÓN");
                atencion = sesion.finalizarAtencion();
                comunicacion.anularAtencion(atencion);
                vistaEmpleado.anularAtencion();
            } else if (command.equals("Cancelar")) {
            	log.info("SOLICITUD DE CANCELACIÓN DE ATENCIÓN");
                atencion = sesion.cancelarAtencion();
                comunicacion.cancelarAtencion(atencion);
                vistaEmpleado.cancelarAtencion();
            } else if (command.equals("Confirmar")) {
            	log.info("SOLICITUD DE CONFIRMACIÓN DE ATENCIÓN");
                atencion = sesion.confirmarAtencion();
                comunicacion.confirmarAtencion(atencion);
                vistaEmpleado.confirmarAtencion();
            }
        } catch (SolicitudException exception) {
        	log.info("FALLÓ AL REALIZAR LA CONEXIÓN CON EL SERVIDOR");
			vistaEmpleado.informarMensaje("Hubo un fallo al realizar la conexión con el servidor");
        }
    }


}
