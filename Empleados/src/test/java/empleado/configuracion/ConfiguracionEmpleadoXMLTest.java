package empleado.configuracion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfiguracionEmpleadoXMLTest {

    final String path = "empleadoConfig.xml";
    ConfiguracionEmpleado configuracionEmpleado;

    @BeforeEach
    void setUp() {
        configuracionEmpleado = new ConfiguracionEmpleadoXML(path);
    }

    @Test
    void getNumeroDeBox() {
        assertEquals(1, configuracionEmpleado.getNumeroDeBox());
    }
}