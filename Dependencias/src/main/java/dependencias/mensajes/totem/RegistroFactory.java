package dependencias.mensajes.totem;

import dependencias.atencion.Cliente;

public interface RegistroFactory {

    public Registro nuevoRegistroExitoso(String mensaje, Cliente cliente);

    public Registro nuevoRegistroFallido(String mensaje, Cliente cliente);

}
