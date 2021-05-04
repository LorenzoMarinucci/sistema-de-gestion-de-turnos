package servidor_central.espera.Queue;

import dependencias.atencion.Atencion;
import lombok.Synchronized;
import servidor_central.espera.FilaDeEspera;

import java.util.Queue;

public abstract class FilaDeEsperaAbstracta implements FilaDeEspera {

    protected Queue<Atencion> fila;
    protected Integer tamañoMaximo = Integer.MAX_VALUE;

    public FilaDeEsperaAbstracta(Integer tamañoMaximo) {
        this.tamañoMaximo = tamañoMaximo;
    }

    public  FilaDeEsperaAbstracta() {

    }
}
