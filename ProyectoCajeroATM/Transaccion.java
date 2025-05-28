package ProyectoCajeroATM;

public abstract class Transaccion implements ITransaccion {

    private int identificador;
    private String fecha;
    private char tipo;
    private double monto;
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;

    public Transaccion(int identificador, String fecha, char tipo, double monto, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        this.identificador = identificador;
        this.fecha = fecha;
        this.tipo = tipo;
        this.monto = monto;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "identificador=" + identificador +
                ", fecha='" + fecha + '\'' +
                ", tipo=" + tipo +
                ", monto=" + monto +
                ", cuentaOrigen=" + (cuentaOrigen != null ? cuentaOrigen.getNumero_de_cuenta() : "null") +
                ", cuentaDestino=" + (cuentaDestino != null ? cuentaDestino.getNumero_de_cuenta() : "null") +
                '}';
    }
}
