package servidor_central.espera.criterios;

import dependencias.atencion.Atencion;

public class DNIDescendente extends Criterio {

    @Override
    public int compare(Atencion atencion1, Atencion atencion2) {
        Integer reingreso = super.compare(atencion1,atencion2);
        if (reingreso == 0) {
            if (atencion1.getCliente().getDNI() > atencion2.getCliente().getDNI()) {
                return -1;
            } else {
                return 1;
            }
        } else {
            return reingreso;
        }
    }

}
