package servidor_central.clientes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dependencias.atencion.Cliente;
import dependencias.interfaces.clientes.ServicioClientes;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ServicioClientesImpl implements ServicioClientes {

    private Logger log = Logger.getLogger("log.server.servicioClientes");

    private List<Cliente> clientes;
    private final Gson gson = new Gson();

    public ServicioClientesImpl(String path) {
        try {
            clientes = cargarClientes(path);
            log.info("CARGA DE CLIENTES EXITOSA");
        } catch (IOException e) {
            log.info("CARGA DE CLIENTES FALLIDA");
            clientes = new ArrayList<>();
        }
    }

    @Override
    public Cliente obtenerCliente(Integer DNI) {
        return clientes.stream().filter((cliente -> cliente.getDNI().equals(DNI))).findFirst().orElse(null);
    }

    private List<Cliente> cargarClientes(String path) throws IOException {
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String st;
        while ((st = br.readLine()) != null) {
            sb.append(st);
        }
        String json = sb.toString();
        Type collectionType = new TypeToken<List<Cliente>>(){}.getType();
        return gson.fromJson(json, collectionType);
    }

}
