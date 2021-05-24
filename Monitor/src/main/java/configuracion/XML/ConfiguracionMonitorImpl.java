package configuracion.XML;

import configuracion.ConfiguracionMonitor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "comunicacion")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class ConfiguracionMonitorImpl implements ConfiguracionMonitor {

    @Getter
    @XmlElement(name = "puertosMonitoreo")
    @XmlList
    private List<Integer> puertosMonitoreo;

}
