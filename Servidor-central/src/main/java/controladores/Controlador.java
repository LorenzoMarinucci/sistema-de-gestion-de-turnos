package controladores;

import atenciones.FilaDeEspera;
import atenciones.FilaDeEsperaPQ;
import comunicacion.ComunicacionServidor;
import comunicacion.InformeRegistro;
import configuracion.Configuracion;
import configuracion.ConfiguracionXML;
import excepciones.DniRepetidoException;
import excepciones.SinCapacidadException;

public class Controlador {

    private Configuracion configuracion;
    private FilaDeEspera filaDeEspera;

    public Controlador(Configuracion configuracion, FilaDeEspera filaDeEspera) {
        this.configuracion = configuracion;
        this.filaDeEspera = filaDeEspera;
        filaDeEspera.setTamañoMaximo(configuracion.getTamañoMaximo());
    }

    public InformeRegistro realizarRegistro(String DNI) {
        InformeRegistro informeRegistro = null;
        try {
            filaDeEspera.agregarAtencion(Integer.parseInt(DNI));
            informeRegistro = new InformeRegistro(true, "Registro realizado con éxito");
        } catch (DniRepetidoException e) {
            e.printStackTrace();
            informeRegistro = new InformeRegistro(false, "El DNI ya se encuentra en la fila de espera");
        } catch (SinCapacidadException e) {
            informeRegistro = new InformeRegistro(false, "Ya ha sido alcanzada la capacidad máxima de la fila de espera");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return informeRegistro;
    }


}
