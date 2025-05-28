package ProyectoCajeroATM;

public class Transferencia extends Transaccion {

    public Transferencia(int identificador, String fecha, char tipo, double monto, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        super(identificador, fecha, tipo, monto, cuentaOrigen, cuentaDestino);
    }

    @Override
    public boolean realizarOperacion() {
        if (getCuentaOrigen().getSaldo() >= getMonto()) {
            getCuentaOrigen().setSaldo(getCuentaOrigen().getSaldo() - getMonto());
            getCuentaDestino().setSaldo(getCuentaDestino().getSaldo() + getMonto());
            System.out.println("Transferencia exitosa de " + getMonto() + " de la cuenta " 
            + getCuentaOrigen().getNumero_de_cuenta() + " a la cuenta " + getCuentaDestino().getNumero_de_cuenta());
            return true;
        } else {
            System.out.println("Fondos insuficientes para transferencia.");
            return false;
        }
    }
}
