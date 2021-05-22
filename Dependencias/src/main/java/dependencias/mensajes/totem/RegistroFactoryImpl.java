package dependencias.mensajes.totem;

public class RegistroFactoryImpl implements RegistroFactory {

    private static final RegistroFactoryImpl INSTANCE = new RegistroFactoryImpl();

    private RegistroFactoryImpl() {}

    public static RegistroFactoryImpl getInstance() {
        return INSTANCE;
    }

    public Registro nuevoRegistroExitoso(String mensaje) {
        return new Registro(true, mensaje);
    }

    public Registro nuevoRegistroFallido(String mensaje) {
        return new Registro(false, mensaje);
    }

}
