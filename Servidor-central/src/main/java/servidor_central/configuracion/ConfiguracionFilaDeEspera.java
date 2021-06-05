package servidor_central.configuracion;

import java.util.Map;

public interface ConfiguracionFilaDeEspera {

    Integer getTamañoFila();
    Map<String, Integer> getPrioridades();
    String getCriterioPrioridad();

}
