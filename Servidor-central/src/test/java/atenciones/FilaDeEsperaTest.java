package atenciones;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilaDeEsperaTest {

    FilaDeEspera filaDeEspera;

    @BeforeEach
    void setUp() {
        filaDeEspera = new FilaDeEspera();
    }

    @Test
    void cargaAtributosTest() {
        assertEquals(20, filaDeEspera.getTamañoMaximo());
    }

}