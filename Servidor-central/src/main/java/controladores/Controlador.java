package controladores;

import atencion.Atencion;
import espera.FilaDeEspera;
import configuracion.Configuracion;
import excepciones.DniRepetidoException;
import excepciones.SinCapacidadException;
import mensaje.Registro;

public class Controlador {

    private Configuracion configuracion;
    private FilaDeEspera filaDeEspera;

    public Controlador(Configuracion configuracion, FilaDeEspera filaDeEspera) {
        this.configuracion = configuracion;
        this.filaDeEspera = filaDeEspera;
        filaDeEspera.setTamañoMaximo(configuracion.getTamañoMaximo());
    }

    public Registro realizarRegistro(String DNI) {
        Registro informeRegistro = null;
        System.out.println("Nueva solicitud de registro. DNI: " + DNI);
        try {
            filaDeEspera.agregarAtencion(Integer.parseInt(DNI));
            informeRegistro = new Registro(true, "Registro realizado con éxito");
        } catch (DniRepetidoException e) {
            e.printStackTrace();
            informeRegistro = new Registro(false, e.getMessage());
        } catch (SinCapacidadException e) {
            e.printStackTrace();
            informeRegistro = new Registro(false, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return informeRegistro;
    }

    public Atencion solicitudAtencion() {
        Atencion atencion = null;
        System.out.println("Nueva solicitud de atencion");
        try {
            atencion = filaDeEspera.sacarNuevaAtencion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return atencion;
    }


    public void cancelarAtencion(Atencion atencion) {
        filaDeEspera.reingresarAtencion(atencion);
    }
}
