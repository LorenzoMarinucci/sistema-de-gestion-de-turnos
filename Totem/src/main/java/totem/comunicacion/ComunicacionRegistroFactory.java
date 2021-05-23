package totem.comunicacion;

import dependencias.mensajes.totem.RegistroFactory;
import dependencias.mensajes.totem.SolicitudTotemFactory;
import totem.configuracion.ConfiguracionTotem;

public class ComunicacionRegistroFactory {

    private static final ComunicacionRegistroFactory INSTANCE = new ComunicacionRegistroFactory();

    private ComunicacionRegistroFactory() {

    }

    public static ComunicacionRegistroFactory getInstance() {
        return INSTANCE;
    }

    public ComunicacionRegistro crearComunicacionRegistro(String host, RegistroFactory registroFactory,
                                                          ConfiguracionTotem configuracionTotem, SolicitudTotemFactory solicitudTotemFactory) {
        return new ComunicacionRegistro(host, configuracionTotem.getPrimario(),
                configuracionTotem.getSecundario(), registroFactory, solicitudTotemFactory);
    }

}
