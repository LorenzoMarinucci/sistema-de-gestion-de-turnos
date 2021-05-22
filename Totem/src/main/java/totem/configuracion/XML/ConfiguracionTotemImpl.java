package totem.configuracion.XML;

import lombok.Getter;
import lombok.NoArgsConstructor;
import totem.configuracion.ConfiguracionTotem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "configuracion")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class ConfiguracionTotemImpl implements ConfiguracionTotem {

    @Getter
    @XmlElement(name = "primario")
    private Integer primario;

    @Getter
    @XmlElement(name = "secundario")
    private Integer secundario;

}
