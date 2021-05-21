package servidor_central.estado;

public interface EstadoServidor {

    Boolean isPrimario();
    void establecerSecundario();
    void establecerPrimario();

}
