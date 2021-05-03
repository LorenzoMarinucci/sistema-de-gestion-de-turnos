package servidor_central;

import dependencias.atencion.Tipo;
import servidor_central.espera.FilaDeEsperaPQ;
import servidor_central.comunicacion.ComunicacionTotem;
import servidor_central.comunicacion.ComunicacionTotemImpl;
import servidor_central.configuracion.ConfiguracionXML;
import servidor_central.controladores.Controlador;

import java.util.HashMap;
import java.util.Map;

public class Server {

    public static void main(String[] args) {
        Map<Tipo, Integer> prioridades = new HashMap<>();
        initPrioridades(prioridades);
        ComunicacionTotem comunicacionServidor = new ComunicacionTotemImpl(new Controlador(new ConfiguracionXML(), new FilaDeEsperaPQ(prioridades)));
    }

    private static void initPrioridades(Map<Tipo, Integer> prioridades) {
        prioridades.put(Tipo.NUEVA, 0);
        prioridades.put(Tipo.REINGRESADA, 10);
    }

}
