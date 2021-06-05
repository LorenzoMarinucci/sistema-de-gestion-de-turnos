package servidor_central.espera.criterios;

import dependencias.atencion.Atencion;
import dependencias.atencion.Tipo;

import java.util.Comparator;

public abstract class Criterio implements Comparator<Atencion> {

    public int compare(Atencion atencion1, Atencion atencion2) {
        if (atencion1.getTipo().equals(atencion2.getTipo())) {
            return 0;
        } else {
            if (atencion1.getTipo().equals(Tipo.REINGRESADA)) {
                return -1;
            } else {
                return 1;
            }
        }
    }

}
