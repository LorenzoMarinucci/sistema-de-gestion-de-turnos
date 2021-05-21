package servidor_central.configuracion.XML;

import lombok.Getter;
import lombok.NoArgsConstructor;
import servidor_central.configuracion.ConfiguracionComunicacionServer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "comunicacion")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class ConfiguracionComunicacionServerImpl implements ConfiguracionComunicacionServer {

    @Getter
    @XmlElement(name = "puertoTotem")
    private Integer puertoTotem;

    @Getter
    @XmlElement(name = "puertoEmpleados")
    private Integer puertoEmpleado;

    @Getter
    @XmlElement(name = "puertoTelevisor")
    private Integer puertoTelevisor;

    @Getter
    @XmlElement(name = "primario")
    public Boolean primario;
}
