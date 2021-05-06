package servidor_central.configuracion.XML;

import lombok.Getter;
import lombok.NoArgsConstructor;
import servidor_central.configuracion.ConfiguracionFilaDeEspera;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

@XmlRootElement(name = "espera")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class ConfiguracionFilaDeEsperaImpl implements ConfiguracionFilaDeEspera {

    @Getter
    @XmlElement(name = "tamaño")
    private Integer tamañoFila;

    @Getter
    @XmlElement(name = "prioridades")
    private Map<String, Integer> prioridades;

}
