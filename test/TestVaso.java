import static org.junit.Assert.*;
import org.junit.Test;

public class TestVaso {

    @Test
    public void deberiaDevolverVerdaderoSiExistenVasos() {
        Vaso vasosPequenos = new Vaso(2, 3);

        boolean resultado = vasosPequenos.hasVasos(1);

        assertTrue(resultado);
    }

    @Test
    public void deberiaDevolverFalsoSiNoExistenVasos() {
        Vaso vasosPequenos = new Vaso(1, 3);

        boolean resultado = vasosPequenos.hasVasos(2);

        assertFalse(resultado);
    }

    @Test
    public void deberiaRestarCantidadDeVasos() {
        Vaso vasosPequenos = new Vaso(5, 3);

        vasosPequenos.giveVasos(1);

        assertEquals(4, vasosPequenos.getCantidadVasos());
    }
}