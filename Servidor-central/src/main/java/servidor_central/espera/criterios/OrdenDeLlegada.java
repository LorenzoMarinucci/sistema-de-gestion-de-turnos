package servidor_central.espera.criterios;

import dependencias.atencion.Atencion;

public class OrdenDeLlegada extends Criterio {

    @Override
    public int criterio(Atencion atencion1, Atencion atencion2) {
        if (atencion1.getLlegada().before(atencion2.getLlegada())) {
            return -1;
        } else {
            return 1;
        }
    }

}
