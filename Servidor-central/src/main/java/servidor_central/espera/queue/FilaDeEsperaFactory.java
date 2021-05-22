package servidor_central.espera.queue;

import dependencias.mensajes.totem.RegistroFactory;
import servidor_central.configuracion.ConfiguracionFilaDeEspera;

public class FilaDeEsperaFactory {

    private static final FilaDeEsperaFactory INSTANCE = new FilaDeEsperaFactory();

    private FilaDeEsperaFactory() {

    }

    public static FilaDeEsperaFactory getInstance() {
        return INSTANCE;
    }

    public FilaDeEsperaPQ crearFilaDeEspera(ConfiguracionFilaDeEspera configuracionFilaDeEspera, RegistroFactory registroFactory) {
        return new FilaDeEsperaPQ(configuracionFilaDeEspera.getTamañoFila(), configuracionFilaDeEspera.getPrioridades(),
                registroFactory);
    }
}
