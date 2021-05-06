package empleado.configuracion.XML;

import empleado.configuracion.ConfiguracionSesion;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "configuracionSesion")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class ConfiguracionSesionImpl implements ConfiguracionSesion {

    @Getter
    @XmlElement(name = "box")
    private Integer box;

}
