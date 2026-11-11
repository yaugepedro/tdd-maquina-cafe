import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestSistemaCafe {
    private MaquinaDeCafe maquina;
    private Cafetera cafetera;
    private Azucarero azucarero;
    private Vaso pequeno;
    private Vaso mediano;
    private Vaso grande;

    @Before
    public void preparar() {
        cafetera = new Cafetera(50);
        azucarero = new Azucarero(20);
        pequeno = new Vaso(5, 3);
        mediano = new Vaso(5, 5);
        grande = new Vaso(5, 7);

        maquina = new MaquinaDeCafe();
        maquina.setCafetera(cafetera);
        maquina.setAzucarero(azucarero);
        maquina.setVasosPequenos(pequeno);
        maquina.setVasosMedianos(mediano);
        maquina.setVasosGrandes(grande);
    }

    @Test
    public void hayCafeSuficiente() {
        Cafetera recipiente = new Cafetera(10);
        assertTrue(recipiente.hasCafe(5));
    }

    @Test
    public void noHayCafeSuficiente() {
        Cafetera recipiente = new Cafetera(10);
        assertFalse(recipiente.hasCafe(11));
    }

    @Test
    public void descontarCafe() {
        Cafetera recipiente = new Cafetera(10);
        recipiente.giveCafe(7);
        assertEquals(3, recipiente.getCantidadCafe());
    }

    @Test
    public void hayAzucarSuficiente() {
        Azucarero recipiente = new Azucarero(10);
        assertTrue(recipiente.hasAzucar(5));
        assertTrue(recipiente.hasAzucar(10));
    }

    @Test
    public void noHayAzucarSuficiente() {
        Azucarero recipiente = new Azucarero(10);
        assertFalse(recipiente.hasAzucar(15));
    }

    @Test
    public void descontarAzucar() {
        Azucarero recipiente = new Azucarero(10);
        recipiente.giveAzucar(5);
        assertEquals(5, recipiente.getCantidadAzucar());
        recipiente.giveAzucar(2);
        assertEquals(3, recipiente.getCantidadAzucar());
    }

    @Test
    public void seleccionarPequeno() {
        assertSame(pequeno, maquina.getTipoDeVaso("pequeno"));
        assertEquals(3, pequeno.getContenido());
    }

    @Test
    public void seleccionarMediano() {
        assertSame(mediano, maquina.getTipoDeVaso("mediano"));
        assertEquals(5, mediano.getContenido());
    }

    @Test
    public void seleccionarGrande() {
        assertSame(grande, maquina.getTipoDeVaso("grande"));
        assertEquals(7, grande.getContenido());
    }

    @Test
    public void avisarSiFaltanVasos() {
        assertEquals("No hay Vasos",
            maquina.getVasoDeCafe(pequeno, 10, 2));
    }

    @Test
    public void avisarSiFaltaCafe() {
        cafetera.setCantidadCafe(2);
        assertEquals("No hay Cafe",
            maquina.getVasoDeCafe(pequeno, 1, 2));
    }

    @Test
    public void avisarSiFaltaAzucar() {
        azucarero.setCantidadAzucar(2);
        assertEquals("No hay Azucar",
            maquina.getVasoDeCafe(pequeno, 1, 3));
    }

    @Test
    public void servirDescuentaCafe() {
        maquina.getVasoDeCafe(pequeno, 1, 3);
        assertEquals(47, cafetera.getCantidadCafe());
    }

    @Test
    public void servirDescuentaVaso() {
        maquina.getVasoDeCafe(pequeno, 1, 3);
        assertEquals(4, pequeno.getCantidadVasos());
    }

    @Test
    public void servirDescuentaAzucar() {
        maquina.getVasoDeCafe(pequeno, 1, 3);
        assertEquals(17, azucarero.getCantidadAzucar());
    }

    @Test
    public void confirmarPedidoCompletado() {
        assertEquals("Felicitaciones",
            maquina.getVasoDeCafe(pequeno, 1, 3));
    }

    @Test
    public void pedidoFallidoNoConsumeRecursos() {
        azucarero.setCantidadAzucar(0);
        assertEquals("No hay Azucar",
            maquina.getVasoDeCafe(pequeno, 1, 3));
        assertEquals(5, pequeno.getCantidadVasos());
        assertEquals(50, cafetera.getCantidadCafe());
        assertEquals(0, azucarero.getCantidadAzucar());
    }

    @Test
    public void servirSinAzucar() {
        azucarero.setCantidadAzucar(0);
        assertEquals("Felicitaciones",
            maquina.getVasoDeCafe(pequeno, 1, 0));
        assertEquals(0, azucarero.getCantidadAzucar());
    }

    @Test
    public void servirVariosVasos() {
        assertEquals("Felicitaciones",
            maquina.getVasoDeCafe(mediano, 2, 2));
        assertEquals(3, mediano.getCantidadVasos());
        assertEquals(40, cafetera.getCantidadCafe());
        assertEquals(16, azucarero.getCantidadAzucar());
    }
}
