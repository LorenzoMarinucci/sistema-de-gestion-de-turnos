package servidor_central.espera.criterios;

import dependencias.atencion.Atencion;

public class DNIDescendente extends Criterio {

    @Override
    public int criterio(Atencion atencion1, Atencion atencion2) {
        if (atencion1.getCliente().getDNI() > atencion2.getCliente().getDNI()) {
            return -1;
        } else {
            return 1;
        }
    }

}
