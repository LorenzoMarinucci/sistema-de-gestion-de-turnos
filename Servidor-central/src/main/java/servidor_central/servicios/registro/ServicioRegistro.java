package servidor_central.servicios.registro;

import dependencias.atencion.Cliente;

public interface ServicioRegistro {

    void emitirRegistro(String operacion, Cliente cliente);
    void emitirRegistro(String operacion, Cliente cliente, Integer box);

}
