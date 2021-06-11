package servidor_central.configuracion.XML;

import lombok.Getter;
import lombok.NoArgsConstructor;
import servidor_central.configuracion.ConfiguracionServicioClientes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "clientes")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class ConfiguracionServicioClientesImpl implements ConfiguracionServicioClientes {

    @XmlElement(name = "path")
    @Getter
    private String path;

}
