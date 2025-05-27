package ProyectoCajeroATM;

public class Tarjeta {
    private int numero;
    private Cuenta cuenta;

    public Tarjeta(int numero, Cuenta cuenta) {
        this.numero = numero;
        this.cuenta = cuenta;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
