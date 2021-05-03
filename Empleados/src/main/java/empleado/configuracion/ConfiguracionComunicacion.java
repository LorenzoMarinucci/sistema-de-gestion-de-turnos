package empleado.configuracion;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "configuracionComunicacion")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class ConfiguracionComunicacion {

    @Getter
    @XmlElement(name = "puerto")
    private Integer puerto;

}
