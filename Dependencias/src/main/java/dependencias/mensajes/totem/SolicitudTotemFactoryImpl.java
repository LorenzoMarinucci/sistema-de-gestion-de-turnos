package dependencias.mensajes.totem;

import dependencias.mensajes.televisor.SolicitudTelevisorFactoryImpl;

public class SolicitudTotemFactoryImpl implements SolicitudTotemFactory{

    private static final SolicitudTotemFactoryImpl INSTANCE = new SolicitudTotemFactoryImpl();

    private SolicitudTotemFactoryImpl() {}

    public static SolicitudTotemFactoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public SolicitudTotem solicitudRegistro(Integer DNI, Boolean primario) {
        return new SolicitudTotem(DNI, primario);
    }

}
