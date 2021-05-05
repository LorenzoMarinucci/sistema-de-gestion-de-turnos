package dependencias.mensajes.totem;

public class RegistroFactory {

    public static Registro nuevoRegistroExitoso(String mensaje) {
        return new Registro(true, mensaje);
    }

    public static Registro nuevoRegistroFallido(String mensaje) {
        return new Registro(false, mensaje);
    }

}
