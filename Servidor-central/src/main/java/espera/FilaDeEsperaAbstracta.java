package espera;

import atencion.Atencion;

import java.util.Queue;

public abstract class FilaDeEsperaAbstracta implements FilaDeEspera {

    protected Queue<Atencion> fila;
    protected Integer tamañoMaximo = Integer.MAX_VALUE;

    @Override
    public void setTamañoMaximo(Integer tamañoMaximo) {
        this.tamañoMaximo = tamañoMaximo;
    }

}
