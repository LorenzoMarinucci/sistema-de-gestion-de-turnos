package servidor_central.espera.criterios;

import dependencias.atencion.Atencion;

import java.util.Map;

public class CategoriaCliente extends Criterio {

    private Map<String, Integer> categorias;

    public CategoriaCliente(Map<String, Integer> categorias) {
        this.categorias = categorias;
    }

    @Override
    public int criterio(Atencion atencion1, Atencion atencion2) {
        if (categorias.getOrDefault(atencion1.getCliente().getCategoria().toString(), 0) >
                categorias.getOrDefault(atencion2.getCliente().getCategoria().toString(), 0)) {
            return -1;
        } else {
            return 1;
        }
    }

}
