package dependencias.mensajes.totem;

public interface RegistroFactory {

    public Registro nuevoRegistroExitoso(String mensaje);

    public Registro nuevoRegistroFallido(String mensaje);

}
