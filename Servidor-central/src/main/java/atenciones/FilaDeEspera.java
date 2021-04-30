package atenciones;

public interface FilaDeEspera {

    void agregarAtencion(Integer DNI) throws Exception;
    Atencion sacarNuevaAtencion() throws Exception;
    void reingresarAtencion(Atencion atencion);
    void setTamañoMaximo(Integer tamañoMaximo);

}
