package televisor.configuracion.XML;

import televisor.configuracion.ConfiguracionComunicacionTelevisor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "comunicacionConfig")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class ConfiguracionComunicacionImpl implements ConfiguracionComunicacionTelevisor {

    @Getter
    @XmlElement(name = "puerto")
    private Integer puerto;

}