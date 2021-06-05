package totem.vistas;

import dependencias.mensajes.totem.Registro;

public interface VistaFin extends Vista {

    void iniciarTimeout(Integer milisegundos);
    void informarResultado(Registro registro);
}
