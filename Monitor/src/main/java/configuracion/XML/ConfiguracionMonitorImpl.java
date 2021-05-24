package configuracion.XML;

import configuracion.ConfiguracionMonitor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "comunicacion")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class ConfiguracionMonitorImpl implements ConfiguracionMonitor {

    @Getter
    @XmlElement(name = "puertos")
    private List<Integer> puertos;

}
