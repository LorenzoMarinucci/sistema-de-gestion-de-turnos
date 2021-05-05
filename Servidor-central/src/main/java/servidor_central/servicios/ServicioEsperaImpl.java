package servidor_central.servicios;

import dependencias.atencion.Atencion;
import dependencias.mensajes.totem.Registro;
import servidor_central.espera.FilaDeEspera;

import java.util.logging.Logger;

public class ServicioEsperaImpl implements ServicioEspera {

    private static final Logger log = Logger.getLogger("log.totem.servicioEspera");

    private FilaDeEspera filaDeEspera;

    public ServicioEsperaImpl(FilaDeEspera filaDeEspera) {
        this.filaDeEspera = filaDeEspera;
    }

    public Registro realizarRegistro(String DNI) {
        log.info("REALIZANDO NUEVO REGISTRO. DNI: " + DNI);
        Registro informeRegistro = null;
        informeRegistro = filaDeEspera.agregarAtencion(Integer.parseInt(DNI));
        if (informeRegistro.isRegistroExitoso()) {
            log.info("REGISTRO EXITOSO");
        } else {
            log.info("REGISTRO FALLIDO. " + informeRegistro.getMensaje());
        }
        return informeRegistro;
    }

    public Atencion solicitudAtencion(Integer box) {
        log.info("SOLICITUD DE ATENCION. BOX: " + box);
        Atencion atencion = null;
        try {
            atencion = filaDeEspera.sacarNuevaAtencion();
            atencion.setBox(box);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return atencion;
    }


    public void cancelarAtencion(Atencion atencion) {
        log.info("CANCELANDO SOLICITUD DE ATENCIÓN");
        filaDeEspera.reingresarAtencion(atencion);
    }

}
