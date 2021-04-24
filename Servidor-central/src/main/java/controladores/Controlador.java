package controladores;

import atenciones.FilaDeEsperaPQ;
import configuracion.Configuracion;
import configuracion.ConfiguracionXML;

public class Controlador {

    private Configuracion configuracion;
    private FilaDeEsperaPQ filaDeEspera;

    public Controlador() {
        this.configuracion = new ConfiguracionXML();
        this.filaDeEspera = new FilaDeEsperaPQ(this.configuracion.getTamañoMaximo());
    }

}
