package configuracion.XML;

import configuracion.ConfiguracionTelevisor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "televisorConfig")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class ConfiguracionTelevisorImpl implements ConfiguracionTelevisor {

    @Getter
    @XmlElement(name = "lugares")
    private Integer lugares;

}
