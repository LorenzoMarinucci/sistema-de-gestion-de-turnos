package monitor.configuracion.XML;

import monitor.configuracion.ConfiguracionMonitor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "comunicacion")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class ConfiguracionMonitorImpl implements ConfiguracionMonitor {

    @Getter
    @XmlElementWrapper(name = "puertosMonitoreo")
    @XmlElement(name = "puerto")
    private List<Integer> puertosMonitoreo;

}
