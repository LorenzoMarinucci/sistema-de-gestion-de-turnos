package dependencias.mensajes.totem;

import dependencias.atencion.Cliente;

public class RegistroFactoryImpl implements RegistroFactory {

    private static final RegistroFactoryImpl INSTANCE = new RegistroFactoryImpl();

    private RegistroFactoryImpl() {}

    public static RegistroFactoryImpl getInstance() {
        return INSTANCE;
    }

    public Registro nuevoRegistroExitoso(String mensaje, Cliente cliente) {
        return new Registro(true, mensaje, cliente);
    }

    public Registro nuevoRegistroFallido(String mensaje, Cliente cliente) {
        return new Registro(false, mensaje, cliente);
    }

}
