package servidor_central.configuracion;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

@XmlRootElement(name = "espera")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class ConfiguracionFilaDeEspera {

    @Getter
    @XmlElement(name = "tamaño")
    private Integer tamañoFila;

    @Getter
    @XmlElement(name = "prioridades")
    private Map<String, Integer> prioridades;

}
