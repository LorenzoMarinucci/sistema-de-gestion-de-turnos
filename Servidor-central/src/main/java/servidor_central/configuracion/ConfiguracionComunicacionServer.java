package servidor_central.configuracion;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "comunicacion")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class ConfiguracionComunicacionServer {

    @Getter
    @XmlElement(name = "puertoTotem")
    private Integer puertoTotem;

    @Getter
    @XmlElement(name = "puertoEmpleados")
    private Integer puertoEmpleado;

    @Getter
    @XmlElement(name = "puertoTelevisor")
    private Integer puertoTelevisor;

}
