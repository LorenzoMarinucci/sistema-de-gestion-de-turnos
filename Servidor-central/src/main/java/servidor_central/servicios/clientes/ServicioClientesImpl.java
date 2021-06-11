package servidor_central.servicios.clientes;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import dependencias.atencion.Cliente;
import dependencias.interfaces.clientes.ServicioClientes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

public class ServicioClientesImpl implements ServicioClientes {

    private Logger log = Logger.getLogger("log.server.servicioClientes");
    private String path;

    public ServicioClientesImpl(String path) {
        this.path = path;
    }

    @Override
    public Cliente obtenerCliente(Integer DNI) {
        try {
            InputStream inputStream = Files.newInputStream(Path.of(this.path));
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
            reader.beginArray();
            while (reader.hasNext()) {
                Cliente cliente = new Gson().fromJson(reader, Cliente.class);
                if (cliente.getDNI().equals(DNI)) {
                    log.info("CLIENTE ENCONTRADO: " + cliente.toString());
                    reader.close();
                    return cliente;
                }
            }
            reader.endArray();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
