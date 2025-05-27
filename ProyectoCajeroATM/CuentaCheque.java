package ProyectoCajeroATM;

public class CuentaCheque extends Cuenta {
    private double limiteDiario;

    public CuentaCheque(int numero_de_cuenta, Cliente cliente, double saldo, double limiteDiario) {
        super(numero_de_cuenta, cliente, saldo);
        this.limiteDiario = limiteDiario;
        this.tipo = 'D';
    }

    public double getLimiteDiario() {
        return limiteDiario;
    }

    public void setLimiteDiario(double limiteDiario) {
        this.limiteDiario = limiteDiario;
    }

    @Override
    public String toString() {
        return "CuentaDebito{" +
                "numero_de_cuenta=" + getNumero_de_cuenta() +
                ", cliente=" + (getCliente() != null ? getCliente().toString() : "null") +
                ", limiteDiario=" + limiteDiario +
                '}';
    }
}