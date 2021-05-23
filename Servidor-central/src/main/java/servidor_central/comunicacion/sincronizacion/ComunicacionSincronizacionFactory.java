package servidor_central.comunicacion.sincronizacion;

import servidor_central.configuracion.ConfiguracionComunicacionServer;

public class ComunicacionSincronizacionFactory {

    private static final ComunicacionSincronizacionFactory INSTANCE = new ComunicacionSincronizacionFactory();

    private ComunicacionSincronizacionFactory() {

    }

    public static ComunicacionSincronizacionFactory getInstance() {
        return INSTANCE;
    }

    public ComunicacionSincronizacion crearComunicacionSincronizacion(ConfiguracionComunicacionServer configuracionComunicacionServer, String host) {
        return new ComunicacionSincronizacion(host,configuracionComunicacionServer.getPuertoSincronizacion());
    }
}
