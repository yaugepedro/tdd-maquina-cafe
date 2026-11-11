public class Azucarero {
    private int cantidadAzucar;

    public Azucarero(int cantidadAzucar) {
        this.cantidadAzucar = cantidadAzucar;
    }

    public void setCantidadAzucar(int cantidadAzucar) {
        this.cantidadAzucar = cantidadAzucar;
    }

    public int getCantidadAzucar() {
        return cantidadAzucar;
    }

    public boolean hasAzucar(int cantidad) {
        return cantidadAzucar >= cantidad;
    }

    public void giveAzucar(int cantidad) {
        cantidadAzucar -= cantidad;
    }
}
