package configuracion;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConfiguracionXMLTest {

	Configuracion configuracion;
	
	@BeforeEach
	void setUp() {
		configuracion = new ConfiguracionXML();
	}
	
	@Test
	void getLugaresTelevisorTest() {
		assertEquals(Integer.valueOf(10), configuracion.getLugaresTelevisor());
	}
	

}
