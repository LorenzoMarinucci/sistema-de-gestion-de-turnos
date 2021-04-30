package vistas;

public interface VistaFin extends Vista {

    void iniciarTimeout(Integer milisegundos);
    void informarResultado(Boolean exitoso, String mensaje);
}
