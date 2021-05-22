package empleado.configuracion.XML;

import empleado.configuracion.ConfiguracionComunicacion;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "configuracionComunicacion")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class ConfiguracionComunicacionImpl implements ConfiguracionComunicacion {

    @Getter
    @XmlElement(name = "primario")
    private Integer primario;

    @Getter
    @XmlElement(name = "secundario")
    private Integer secundario;

}
