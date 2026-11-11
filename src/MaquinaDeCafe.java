public class MaquinaDeCafe {
    private Cafetera cafetera;
    private Vaso vasosPequenos;
    private Vaso vasosMedianos;
    private Vaso vasosGrandes;
    private Azucarero azucarero;

    public void setCafetera(Cafetera cafetera) {
        this.cafetera = cafetera;
    }

    public Cafetera getCafetera() {
        return cafetera;
    }

    public void setVasosPequenos(Vaso vasos) {
        vasosPequenos = vasos;
    }

    public Vaso getVasosPequenos() {
        return vasosPequenos;
    }

    public void setVasosMedianos(Vaso vasos) {
        vasosMedianos = vasos;
    }

    public Vaso getVasosMedianos() {
        return vasosMedianos;
    }

    public void setVasosGrandes(Vaso vasos) {
        vasosGrandes = vasos;
    }

    public Vaso getVasosGrandes() {
        return vasosGrandes;
    }

    public void setAzucarero(Azucarero azucarero) {
        this.azucarero = azucarero;
    }

    public Azucarero getAzucarero() {
        return azucarero;
    }

    public Vaso getTipoDeVaso(String tipo) {
        if ("pequeno".equalsIgnoreCase(tipo)) {
            return vasosPequenos;
        }
        if ("mediano".equalsIgnoreCase(tipo)) {
            return vasosMedianos;
        }
        if ("grande".equalsIgnoreCase(tipo)) {
            return vasosGrandes;
        }
        return null;
    }

    public String getVasoDeCafe(
            Vaso vaso, int cantidadVasos, int cantidadAzucar) {

        if (vaso == null || cantidadVasos <= 0 || cantidadAzucar < 0) {
            return "Pedido invalido";
        }

        int cafeNecesario = vaso.getContenido() * cantidadVasos;
        int azucarNecesaria = cantidadAzucar * cantidadVasos;

        if (!vaso.hasVasos(cantidadVasos)) {
            return "No hay Vasos";
        }
        if (!cafetera.hasCafe(cafeNecesario)) {
            return "No hay Cafe";
        }
        if (!azucarero.hasAzucar(azucarNecesaria)) {
            return "No hay Azucar";
        }

        vaso.giveVasos(cantidadVasos);
        cafetera.giveCafe(cafeNecesario);
        azucarero.giveAzucar(azucarNecesaria);

        return "Felicitaciones";
    }
}
