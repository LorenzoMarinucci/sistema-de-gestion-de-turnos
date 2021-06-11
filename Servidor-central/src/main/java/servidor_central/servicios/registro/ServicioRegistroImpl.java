package servidor_central.servicios.registro;

import dependencias.atencion.Cliente;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

public class ServicioRegistroImpl implements ServicioRegistro {

    private Logger log = Logger.getLogger("log.server.servicioRegistro");

    private String path;

    public ServicioRegistroImpl(String path) {
        this.path = path;
    }

    @Override
    public void emitirRegistro(String operacion, Cliente cliente) {
       String registro = String.format("%s Operación: %s, DNI: %s, Nombre: %s, Categoría: %s \n", new Date().toString(),
               operacion.toUpperCase(), cliente.getDNI(), cliente.getNombre(), cliente.getCategoria());
       escribirArchivo(registro);
    }

    @Override
    public void emitirRegistro(String operacion, Cliente cliente, Integer box) {
        String registro = String.format("%s Operación: %s, DNI: %s, Nombre: %s, Categoría: %s, Box: %s \n", new Date().toString(),
                operacion.toUpperCase(), cliente.getDNI(), cliente.getNombre(), cliente.getCategoria(), box);
        escribirArchivo(registro);
    }

    private void escribirArchivo(String registro) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(path, true));
            writer.append(registro);
            writer.close();
            log.info("EL REGISTRO FUE GUARDADO CON EXITO");
        } catch (IOException e) {
            log.info("EL REGISTRO NO FUE GUARDADO");
        }
    }

}
