package main;

import atencion.Tipo;
import espera.FilaDeEsperaPQ;
import comunicacion.ComunicacionServidor;
import comunicacion.ComunicacionServidorImpl;
import configuracion.ConfiguracionXML;
import controladores.Controlador;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<Tipo, Integer> prioridades = new HashMap<>();
        initPrioridades(prioridades);
        ComunicacionServidor comunicacionServidor = new ComunicacionServidorImpl(new Controlador(new ConfiguracionXML(), new FilaDeEsperaPQ(prioridades)));
    }

    private static void initPrioridades(Map<Tipo, Integer> prioridades) {
        prioridades.put(Tipo.NUEVA, 0);
        prioridades.put(Tipo.REINGRESADA, 10);
    }

}
