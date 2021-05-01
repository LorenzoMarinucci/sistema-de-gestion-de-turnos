package comunicacion.configuracion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfiguracionComunicacionXMLTest {

    ConfiguracionComunicacion configuracionComunicacion;
    final String path = "comunicacionConfig.xml";

    @BeforeEach
    void setUp() {
        this.configuracionComunicacion = new ConfiguracionComunicacionXML(path);
    }

    @Test
    void getPuertoTest() {
        assertEquals(10400, configuracionComunicacion.getPuerto());
    }

}