package servidor_central.espera.queue;

import dependencias.interfaces.filaDeEspera.Sincronizacion;
import dependencias.mensajes.totem.RegistroFactory;
import servidor_central.configuracion.ConfiguracionFilaDeEspera;
import servidor_central.espera.criterios.*;

public class FilaDeEsperaFactory {

    private static final FilaDeEsperaFactory INSTANCE = new FilaDeEsperaFactory();

    private FilaDeEsperaFactory() {

    }

    public static FilaDeEsperaFactory getInstance() {
        return INSTANCE;
    }

    public FilaDeEsperaPQ crearFilaDeEspera(ConfiguracionFilaDeEspera configuracionFilaDeEspera) {
        Criterio criterio = obtenerCriterio(configuracionFilaDeEspera);
        return new FilaDeEsperaPQ(configuracionFilaDeEspera.getTamañoFila(), criterio);
    }

    public FilaDeEsperaPQ crearFilaDeEspera(ConfiguracionFilaDeEspera configuracionFilaDeEspera, Sincronizacion sincronizacion) {
        Criterio criterio = obtenerCriterio(configuracionFilaDeEspera);
        FilaDeEsperaPQ filaDeEsperaPQ = new FilaDeEsperaPQ(configuracionFilaDeEspera.getTamañoFila(), criterio);
        filaDeEsperaPQ.establecerFila(sincronizacion.obtenerAtenciones());
        return filaDeEsperaPQ;
    }

    private Criterio obtenerCriterio(ConfiguracionFilaDeEspera configuracionFilaDeEspera) {
        Criterio criterio = null;
        switch (configuracionFilaDeEspera.getCriterioPrioridad()) {
            case "DNIAscendente":
                criterio = new DNIAscendente();
                break;
            case "DNIDescendente":
                criterio = new DNIDescendente();
                break;
            case "OrdenDeLlegada":
                criterio = new OrdenDeLlegada();
                break;
            case "CategoriaCliente":
                criterio = new CategoriaCliente(configuracionFilaDeEspera.getPrioridades());
                break;
        }
        return criterio;
    }
}
