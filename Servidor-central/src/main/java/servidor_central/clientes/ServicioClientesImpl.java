package servidor_central.clientes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dependencias.atencion.Cliente;
import dependencias.interfaces.clientes.ServicioClientes;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ServicioClientesImpl implements ServicioClientes {

    private Logger log = Logger.getLogger("log.server.servicioClientes");

    private Map<Integer,Cliente> clientes = new HashMap<>();

    public ServicioClientesImpl(String path) {
        try {
            cargarClientes(path);
            log.info("CARGA DE CLIENTES EXITOSA");
        } catch (IOException e) {
            log.info("CARGA DE CLIENTES FALLIDA");
        }
    }

    @Override
    public Cliente obtenerCliente(Integer DNI) {
        return clientes.get(DNI);
    }

    private void cargarClientes(String path) throws IOException {
        Gson gson = new Gson();
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String st;
        while ((st = br.readLine()) != null) {
            sb.append(st);
        }
        String json = sb.toString();
        Type collectionType = new TypeToken<List<Cliente>>(){}.getType();
        List<Cliente> listaClientes = gson.fromJson(json, collectionType);
        listaClientes.stream().forEach(cliente -> clientes.put(cliente.getDNI(),cliente));
    }

}
