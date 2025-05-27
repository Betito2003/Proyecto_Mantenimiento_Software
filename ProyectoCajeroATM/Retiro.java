package ProyectoCajeroATM;

public class Retiro extends Transaccion {

    public Retiro(int identificador, String fecha, char tipo, double monto, Cuenta cuentaOrigen) {
        super(identificador, fecha, tipo, monto, cuentaOrigen, null);
    }

    @Override
    public void realizarOperacion() {
        if (cuentaOrigen.getSaldo() >= monto) {
            cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
            System.out.println("Retiro exitoso de " + monto + " de la cuenta " + cuentaOrigen.getNumero_de_cuenta());
        } else {
            System.out.println("Fondos insuficientes para retiro.");
        }
    }
}
