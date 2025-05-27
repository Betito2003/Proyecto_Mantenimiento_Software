package ProyectoCajeroATM;

public class Transferencia extends Transaccion {

    public Transferencia(int identificador, String fecha, char tipo, double monto, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        super(identificador, fecha, tipo, monto, cuentaOrigen, cuentaDestino);
    }

    @Override
    public void realizarOperacion() {
        if (cuentaOrigen.getSaldo() >= monto) {
            cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
            cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
            System.out.println("Transferencia exitosa de " + monto + " de la cuenta " + cuentaOrigen.getNumero_de_cuenta() + " a la cuenta " + cuentaDestino.getNumero_de_cuenta());
        } else {
            System.out.println("Fondos insuficientes para transferencia.");
        }
    }
}
